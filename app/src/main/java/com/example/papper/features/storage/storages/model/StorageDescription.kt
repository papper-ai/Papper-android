package com.example.papper.features.storage.storages.model

import com.example.domain.model.StoragePreviewModelResult

data class StorageDescription(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val list: List<PresentationStoragePreviewModel>,
)

data class PresentationStoragePreviewModel(
    val id: String,
    val title: String,
)

internal fun StoragePreviewModelResult.mapToPresentationModel() = StorageDescription(
    isSuccess = isSuccess,
    code = code,
    msg = msg,
    list = list.map { storagePreviewModel ->
        PresentationStoragePreviewModel(
            id = storagePreviewModel.id,
            title = storagePreviewModel.title
        )
    }
)