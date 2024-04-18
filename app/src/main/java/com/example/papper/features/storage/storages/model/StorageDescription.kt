package com.example.papper.features.storage.storages.model

import com.example.domain.model.StoragePreviewModel

data class StorageDescription(
    val id: String,
    val title: String = "",
)

typealias StoragePreviewModelList = List<StoragePreviewModel>
internal fun StoragePreviewModel.mapToPresentationModel() = StorageDescription(
    id = id,
    title = title
)

internal fun StoragePreviewModelList.mapToPresentationModel() = map { it.mapToPresentationModel() }