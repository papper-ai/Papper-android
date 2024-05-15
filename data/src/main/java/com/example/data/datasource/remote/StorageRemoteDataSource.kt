package com.example.data.datasource.remote

import com.example.data.api.StorageApiService
import com.example.data.base.BaseResponse
import com.example.data.model.storage.AddFileInStorageResponseResult
import com.example.data.model.storage.CreateStorageResponseResult
import com.example.data.model.storage.DeleteFileResponseResult
import com.example.data.model.storage.DeleteStorageResponseResult
import com.example.data.model.storage.FileDataModel
import com.example.data.model.storage.RenameVaultBody
import com.example.data.model.storage.RenameVaultResponseResult
import com.example.data.model.storage.StoragePreviewModel
import com.example.data.model.storage.StoragePreviewResponseResult
import com.example.data.model.storage.StorageResponseResult
import com.example.data.service.AuthService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import java.io.File
import javax.inject.Inject


class StorageRemoteDataSource @Inject constructor(
    private val apiService: StorageApiService,
    private val authService: AuthService,
) {

    suspend fun createStorage(title: String, type: String, listOfFiles: List<File>): CreateStorageResponseResult {
        lateinit var result: CreateStorageResponseResult

        val list = mutableListOf<MultipartBody.Part>()
        val createVaultCredentials = "{\"vault_name\":\"$title\",\"vault_type\":\"$type\"}"
        val createVaultBody = createVaultCredentials.toRequestBody("multipart/form-data".toMediaType())

        for (file in listOfFiles) {
            if (file.extension == "pdf")  {
                list.add(
                    MultipartBody.Part.createFormData(
                        "files",
                        file.name,
                        file.asRequestBody("application/pdf".toMediaTypeOrNull())
                    )
                )
            } else if (file.extension == "docx") {
                list.add(
                    MultipartBody.Part.createFormData(
                        "files",
                        file.name,
                        file.asRequestBody("application/vnd.openxmlformats-officedocument.wordprocessingml.document".toMediaTypeOrNull())
                    )
                )

            } else if (file.extension == "txt") {
                list.add(
                    MultipartBody.Part.createFormData(
                        "files",
                        file.name,
                        file.asRequestBody("text/plain".toMediaTypeOrNull())
                    )
                )
            }
        }

        try {
            val resultFromApi = apiService.createVault(
                createVaultBody,
                list
            )

            if (resultFromApi.isSuccessful) {
                result = CreateStorageResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    id = resultFromApi.body()?.id.orEmpty()
                )
            } else {
                if (resultFromApi.code() == 401) {
                    val token = authService.refreshToken()
                    if (token.isSuccess) {
                        createStorage(title = title, type = type, listOfFiles = listOfFiles)
                    }
                }
                result = CreateStorageResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    id = ""
                )
            }
        } catch (e: IOException) {
            result = CreateStorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                ),
                id = ""
            )
        } catch (e: Exception) {
            result = CreateStorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                ),
                id = ""
            )
        }

        return result
    }

    suspend fun getAllStoragesPreview(): StoragePreviewResponseResult {
        lateinit var result: StoragePreviewResponseResult

        try {
            val resultFromApi = apiService.getVaultPreviews()

            if (resultFromApi.isSuccessful) {
                result = StoragePreviewResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    listOfStoragePreviews = resultFromApi.body()?.map { storagePreviewResponse ->
                        StoragePreviewModel(
                            id = storagePreviewResponse.id,
                            title = storagePreviewResponse.title
                        )
                    } ?: emptyList()
                )
            } else {
                if (resultFromApi.code() == 401) {
                    val token = authService.refreshToken()
                    if (token.isSuccess) {
                        getAllStoragesPreview()
                    }
                }
                result = StoragePreviewResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    listOfStoragePreviews = emptyList()
                )
            }
        } catch (e: IOException) {
            result = StoragePreviewResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                ),
                listOfStoragePreviews = emptyList()
            )
        } catch (e: Exception) {
            result = StoragePreviewResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                ),
                listOfStoragePreviews = emptyList()
            )
        }

        return result
    }

    suspend fun getStorageById(id: String): StorageResponseResult {
        lateinit var result: StorageResponseResult

        try {
            val resultFromApi = apiService.getVaultByID(id)

            if (resultFromApi.isSuccessful) {
                result = StorageResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    id = resultFromApi.body()?.id.orEmpty(),
                    title = resultFromApi.body()?.title.orEmpty(),
                    listOfFiles = resultFromApi.body()?.documents?.map { document ->
                        FileDataModel(
                            id = document.id,
                            title = document.title,
                            text = document.text,
                        )
                    } ?: emptyList()
                )
            } else {
                if (resultFromApi.code() == 401) {
                    val token = authService.refreshToken()
                    if (token.isSuccess) {
                        getStorageById(id = id)
                    }
                }
                result = StorageResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    id = "",
                    title = "",
                    listOfFiles = emptyList(),
                )
            }
        } catch (e: IOException) {
            result = StorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                ),
                id = "",
                title = "",
                listOfFiles = emptyList(),
            )
        } catch (e: Exception) {
            result = StorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                ),
                id = "",
                title = "",
                listOfFiles = emptyList(),
            )
        }

        return result
    }

    suspend fun deleteStorageById(id: String): DeleteStorageResponseResult {
        lateinit var result: DeleteStorageResponseResult

        try {
            val resultFromApi = apiService.deleteVaultByID(id)

            if (resultFromApi.isSuccessful) {
                result = DeleteStorageResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            } else {
                if (resultFromApi.code() == 401) {
                    val token = authService.refreshToken()
                    if (token.isSuccess) {
                        deleteStorageById(id = id)
                    }
                }
                result = DeleteStorageResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            }
        } catch (e: IOException) {
            result = DeleteStorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                )
            )
        } catch (e: Exception) {
            result = DeleteStorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                )
            )
        }

        return result
    }

    suspend fun addFileInStorage(id: String, file: File): AddFileInStorageResponseResult {
        lateinit var result: AddFileInStorageResponseResult

        val vaultIdBody = id.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        var newFile: MultipartBody.Part = when (file.extension) {
            "pdf" -> {
                MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    file.asRequestBody("application/pdf".toMediaTypeOrNull())
                )
            }
            "docx" -> {
                MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    file.asRequestBody("application/vnd.openxmlformats-officedocument.wordprocessingml.document".toMediaTypeOrNull())
                )
            }
            "txt" -> {
                MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    file.asRequestBody("text/plain".toMediaTypeOrNull())
                )
            }

            else -> {
                MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    file.asRequestBody("text/plain".toMediaTypeOrNull())
                )
            }
        }

        try {
            val resultFromApi = apiService.addFileToVault(
                vaultId = vaultIdBody,
                file = newFile
            )

            if (resultFromApi.isSuccessful) {
                result = AddFileInStorageResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    id = resultFromApi.body()?.id.orEmpty(),
                    text = resultFromApi.body()?.text.orEmpty(),
                )
            } else {
                if (resultFromApi.code() == 401) {
                    val token = authService.refreshToken()
                    if (token.isSuccess) {
                        addFileInStorage(id = id, file = file)
                    }
                }
                result = AddFileInStorageResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    ),
                    id = "",
                    text = "",
                )
            }
        } catch (e : IOException) {
            result = AddFileInStorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                ),
                id = "",
                text = "",
            )
        } catch (e: Exception) {
            result = AddFileInStorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                ),
                id = "",
                text = "",
            )
        }

        return result
    }

    suspend fun deleteFileInStorage(vaultId: String, documentId: String): DeleteFileResponseResult {
        lateinit var result: DeleteFileResponseResult

        try {
            val resultFromApi = apiService.deleteFileFromVaultByID(vaultId = vaultId, documentId = documentId)

            if (resultFromApi.isSuccessful) {
                result = DeleteFileResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            } else {
                if (resultFromApi.code() == 401) {
                    val token = authService.refreshToken()
                    if (token.isSuccess) {
                        deleteFileInStorage(vaultId = vaultId, documentId = documentId)
                    }
                }
                result = DeleteFileResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            }
        } catch (e: IOException) {
            result = DeleteFileResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                )
            )
        } catch (e: Exception) {
            result = DeleteFileResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                )
            )
        }

        return result
    }

    suspend fun renameStorage(id: String, title: String): RenameVaultResponseResult {
        lateinit var result: RenameVaultResponseResult

        try {
            val resultFromApi = apiService.renameVault(RenameVaultBody(vaultId = id, title = title))

            if (resultFromApi.isSuccessful) {
                result = RenameVaultResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = true,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            } else {
                if (resultFromApi.code() == 401) {
                    val token = authService.refreshToken()
                    if (token.isSuccess) {
                        renameStorage(id = id, title = title)
                    }
                }
                result = RenameVaultResponseResult(
                    baseResponse = BaseResponse(
                        isSuccess = false,
                        code = resultFromApi.code().toString(),
                        msg = resultFromApi.message(),
                    )
                )
            }
        } catch (e: IOException) {
            result = RenameVaultResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Ошибка подключения к интернету"
                )
            )
        } catch (e: Exception) {
            result = RenameVaultResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = "0",
                    msg = "Неизвестная ошибка"
                )
            )
        }

        return result
    }

}