package com.example.papper.features.storage.storage.model

import com.example.domain.model.storage.StorageModel
import java.io.File

data class PresentationStorageModel(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val id: String,
    val title: String,
    val listOfFiles: List<FilePresentationModel>,
)

data class FilePresentationModel(
    val id: String,
    val title: String,
)

internal fun StorageModel.mapToPresentationModel() =
    PresentationStorageModel(
        isSuccess = isSuccess,
        code = code,
        msg = msg,
        id = id,
        title = title,
        listOfFiles = listOfFiles.map { fileDomainModel ->
            FilePresentationModel(
                id = fileDomainModel.id,
                title = fileDomainModel.title,
            )
        }
    )