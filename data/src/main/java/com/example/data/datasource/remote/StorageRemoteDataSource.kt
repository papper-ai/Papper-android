package com.example.data.datasource.remote

import android.util.Log
import com.example.data.api.StorageApiService
import com.example.data.model.storage.CreateStorageResponse
import com.example.data.model.storage.StoragePreviewModel
import com.example.data.model.storage.StoragePreviewResponse
import com.example.data.model.storage.StorageResponse
import com.example.data.utils.BaseResponseImitation

import kotlinx.coroutines.delay
import java.io.File
import javax.inject.Inject

class StorageRemoteDataSource @Inject constructor(
    private val apiService: StorageApiService,
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