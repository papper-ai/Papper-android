package com.example.papper.features.storage.storage.model

import com.example.domain.model.StorageModel
import java.io.File

data class PresentationStorageModel(
    val id: String,
    val title: String,
    val listOfFiles: List<File>,
)

internal fun StorageModel.mapToPresentationModel() =
    PresentationStorageModel(
        id = id,
        title = title,
        listOfFiles = listOfFiles
    )