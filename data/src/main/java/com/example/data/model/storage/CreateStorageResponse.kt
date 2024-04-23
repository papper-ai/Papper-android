package com.example.data.model.storage

import com.example.data.base.BaseResponse
import com.example.domain.model.storage.CreateStorageResult

data class CreateStorageResponse(
    val baseResponse: BaseResponse,
    val id: String,
)

internal fun CreateStorageResponse.mapToDomainModel() : CreateStorageResult =
    CreateStorageResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        id = id
    )