package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.AddFileInStorageResult

data class AddFileInStorageResponse(
    val baseResponse: BaseResponse,
    val id: String,
)

fun AddFileInStorageResponse.mapToDomainModel(): AddFileInStorageResult = AddFileInStorageResult(
    id = id,
    isSuccess = baseResponse.isSuccess,
    code = baseResponse.code,
    msg = baseResponse.msg,
)