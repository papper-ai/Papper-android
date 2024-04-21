package com.example.data.datasource

import android.util.Log
import com.example.data.api.ApiService
import com.example.data.model.CreateStorageResponse
import com.example.data.model.StoragePreviewModel
import com.example.data.model.StoragePreviewResponse
import com.example.data.model.StorageResponse
import com.example.data.utils.BaseResponseImitation

import kotlinx.coroutines.delay
import java.io.File
import javax.inject.Inject

class StorageRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {

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
        val list = mutableListOf<File>()
        for (i in 1..15) {
            list.add(
                File("$i example example example example.pdf")
            )
            Log.d("Test", "getData: $i")
        }
        return StorageResponse(
            baseResponse = BaseResponseImitation.execute(),
            id = id,
            title = "$id title from remote",
            listOfStorages = list
        )
    }

    suspend fun createStorage(title: String, list: List<File>): CreateStorageResponse {
        delay(1500)
        return CreateStorageResponse(
            baseResponse = BaseResponseImitation.execute(),
            id = "123"
        )
    }

}