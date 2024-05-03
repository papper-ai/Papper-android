package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.StoragePreviewModelResult
import com.google.gson.annotations.SerializedName

data class StoragePreviewResponse(
    val id: String,
    @SerializedName("name")
    val title: String,
)

data class StoragePreviewResponseResult (
    val baseResponse: BaseResponse,
    val listOfStoragePreviews: List<StoragePreviewModel>,
)

data class StoragePreviewModel (
    val id: String,
    val title: String,
)

internal fun StoragePreviewResponseResult.mapToDomainModel() : StoragePreviewModelResult {
    val newList = listOfStoragePreviews.map { storagePreviewModel ->
        com.example.domain.model.storage.StoragePreviewModel(
            id = storagePreviewModel.id,
            title = storagePreviewModel.title
        )
    }
    return StoragePreviewModelResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        list = newList
    )
}
