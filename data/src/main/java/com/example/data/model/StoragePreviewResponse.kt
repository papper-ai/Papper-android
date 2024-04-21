package com.example.data.model

import com.example.data.base.BaseResponse
import com.example.domain.model.StoragePreviewModelResult

data class StoragePreviewResponse (
    val baseResponse: BaseResponse,
    val listOfStoragePreviews: List<StoragePreviewModel>,
)

data class StoragePreviewModel (
    val id: String,
    val title: String,
)

internal fun StoragePreviewResponse.mapToDomainModel() : StoragePreviewModelResult {
    val newList = listOfStoragePreviews.map { storagePreviewModel ->
        com.example.domain.model.StoragePreviewModel(
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
