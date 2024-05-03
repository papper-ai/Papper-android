package com.example.data.datasource.remote

import android.util.Log
import com.example.data.api.StorageApiService
import com.example.data.base.BaseResponse
import com.example.data.model.storage.AddFileInStorageResponse
import com.example.data.model.storage.CreateStorageResponseResult
import com.example.data.model.storage.DeleteFileResponse
import com.example.data.model.storage.FileDataModel
import com.example.data.model.storage.StoragePreviewModel
import com.example.data.model.storage.StoragePreviewResponse
import com.example.data.model.storage.StorageResponse
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

    suspend fun getAllStoragesPreview(): StoragePreviewResponse {
        delay(1500)
        val list = mutableListOf<StoragePreviewModel>()
        for (i in 1..15) {
            list.add(
                StoragePreviewModel(
                    id = "$i",
                    title = "Storage from remote $i"
                )
            )
        }
        return StoragePreviewResponse(baseResponse = BaseResponseImitation.execute(), listOfStoragePreviews = list)
    }

    suspend fun getStorageById(id: String): StorageResponse {
        delay(1500)
        val list = mutableListOf<FileDataModel>()
        for (i in 1..15) {
            list.add(
                FileDataModel(
                    id = "$i",
                    title = "$i example example example example.pdf",
                )
            )
            Log.d("Test", "getData: $i")
        }
        return StorageResponse(
            baseResponse = BaseResponseImitation.execute(),
            id = id,
            title = "$id title from remote",
            listOfFiles = list
        )
    }

    suspend fun addFileInStorage(id: String, file: File): AddFileInStorageResponse {
        delay(1000)
        return AddFileInStorageResponse(baseResponse = BaseResponseImitation.execute(), id = "123")
    }

    suspend fun deleteFileInStorage(id: String): DeleteFileResponse {
        delay(1000)
        return DeleteFileResponse(baseResponse = BaseResponseImitation.execute())
    }

}