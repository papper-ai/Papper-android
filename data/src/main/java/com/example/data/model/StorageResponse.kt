package com.example.data.model

import com.example.data.base.BaseResponse
import java.io.File

data class StorageResponse(
    val baseResponse: BaseResponse,
    val id: String,
    val title: String,
    val listOfStorages: List<File>,
)

internal fun StorageResponse.mapToDomainModel() : com.example.domain.model.StorageModel =
    com.example.domain.model.StorageModel(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        id = id,
        title = title,
        listOfFiles = listOfStorages,
    )