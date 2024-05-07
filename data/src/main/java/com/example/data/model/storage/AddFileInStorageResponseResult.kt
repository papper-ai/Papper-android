package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.AddFileInStorageResult

data class AddFileIntoStorageResponse(
    val id: String,
)

data class AddFileInStorageResponseResult(
    val baseResponse: BaseResponse,
    val id: String,
)

fun AddFileInStorageResponseResult.mapToDomainModel(): AddFileInStorageResult = AddFileInStorageResult(
    id = id,
    isSuccess = baseResponse.isSuccess,
    code = baseResponse.code,
    msg = baseResponse.msg,
)