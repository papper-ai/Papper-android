package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.FileDomainModel
import com.example.domain.model.storage.StorageModel

data class StorageResponse(
    val baseResponse: BaseResponse,
    val id: String,
    val title: String,
    val listOfFiles: List<FileDataModel>,
)

data class FileDataModel(
    val id: String,
    val title: String,
)

internal fun StorageResponse.mapToDomainModel() : StorageModel =
    StorageModel(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        id = id,
        title = title,
        listOfFiles = listOfFiles.map { fileDataModel ->
            FileDomainModel(
                id = fileDataModel.id,
                title = fileDataModel.title,
            )
        },
    )