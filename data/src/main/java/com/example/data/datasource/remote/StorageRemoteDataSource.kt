package com.example.data.datasource.remote

import com.example.data.api.StorageApiService
import com.example.data.base.BaseResponse
import com.example.data.model.storage.AddFileInStorageResponse
import com.example.data.model.storage.CreateStorageResponseResult
import com.example.data.model.storage.DeleteFileResponseResult
import com.example.data.model.storage.DeleteStorageResponseResult
import com.example.data.model.storage.FileDataModel
import com.example.data.model.storage.StoragePreviewModel
import com.example.data.model.storage.StoragePreviewResponseResult
import com.example.data.model.storage.StorageResponseResult
import com.example.data.utils.BaseResponseImitation
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject


class StorageRemoteDataSource @Inject constructor(
    private val apiService: StorageApiService,
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
            result = CreateStorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message(),
                ),
                id = ""
            )
        }

        return result
    }

    suspend fun getAllStoragesPreview(): StoragePreviewResponseResult {
        lateinit var result: StoragePreviewResponseResult

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
            result = StoragePreviewResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message(),
                ),
                listOfStoragePreviews = emptyList()
            )
        }

        return result
    }

    suspend fun getStorageById(id: String): StorageResponseResult {
        lateinit var result: StorageResponseResult

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
                    )
                } ?: emptyList()
            )
        } else {
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

        return result
    }

    suspend fun deleteStorageById(id: String): DeleteStorageResponseResult {
        lateinit var result: DeleteStorageResponseResult

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
            result = DeleteStorageResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message(),
                )
            )
        }

        return result
    }

    suspend fun addFileInStorage(id: String, file: File): AddFileInStorageResponse {
        delay(1000)
        return AddFileInStorageResponse(baseResponse = BaseResponseImitation.execute(), id = "123")
    }

    suspend fun deleteFileInStorage(vaultId: String, documentId: String): DeleteFileResponseResult {
        lateinit var result: DeleteFileResponseResult

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
            result = DeleteFileResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message(),
                )
            )
        }

        return result
    }

}