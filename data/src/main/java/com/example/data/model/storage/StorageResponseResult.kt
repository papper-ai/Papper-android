package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.FileDomainModel
import com.example.domain.model.storage.StorageModel
import com.google.gson.annotations.SerializedName

data class StorageResponse(
    val id: String,
    @SerializedName("name")
    val title: String,
    val documents: List<Document>,
)

data class Document(
    val id: String,
    @SerializedName("name")
    val title: String,
    val text: String,
)


data class StorageResponseResult(
    val baseResponse: BaseResponse,
    val id: String,
    val title: String,
    val listOfFiles: List<FileDataModel>,
)

data class FileDataModel(
    val id: String,
    val title: String,
)

internal fun StorageResponseResult.mapToDomainModel() : StorageModel =
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