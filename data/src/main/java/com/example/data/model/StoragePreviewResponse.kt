package com.example.data.model

data class StoragePreviewResponse (
    val listOfStoragePreviews: List<StoragePreviewModel>,
)

data class StoragePreviewModel (
    val id: String,
    val title: String,
)

internal fun StoragePreviewResponse.mapToDomainModel() : List<com.example.domain.model.StoragePreviewModel> =
    listOfStoragePreviews.map { storagePreviewModel ->
        com.example.domain.model.StoragePreviewModel(
            id = storagePreviewModel.id,
            title = storagePreviewModel.title
        )
    }