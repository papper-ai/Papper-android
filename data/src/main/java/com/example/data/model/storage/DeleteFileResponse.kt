package com.example.data.model.storage

import com.example.data.base.BaseResponse


data class DeleteFileResponseResult(
    val baseResponse: BaseResponse,
)

internal fun DeleteFileResponseResult.mapToDomainModel(): com.example.domain.model.BaseResponse =
    com.example.domain.model.BaseResponse(
        isSuccess = baseResponse.isSuccess,
        msg = baseResponse.msg,
    )