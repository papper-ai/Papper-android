package com.example.data.datasource

import com.example.data.model.StoragePreviewModel
import com.example.data.model.StoragePreviewResponse
import kotlinx.coroutines.delay

class StorageRemoteDataSource {

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
        return StoragePreviewResponse(listOfStoragePreviews = list)
    }

}