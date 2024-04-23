package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.StorageModel
import java.io.File

data class StorageResponse(
    val baseResponse: BaseResponse,
    val id: String,
    val title: String,
    val listOfStorages: List<File>,
)

internal fun StorageResponse.mapToDomainModel() : StorageModel =
    StorageModel(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        id = id,
        title = title,
        listOfFiles = listOfStorages,
    )