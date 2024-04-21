package com.example.data.model

import com.example.data.base.BaseResponse
import com.example.domain.model.SendMessageResult

data class SendMessageResponse(
    val baseResponse: BaseResponse,
)

internal fun SendMessageResponse.mapToDomainModel() = SendMessageResult(
    isSuccess = baseResponse.isSuccess,
    code = baseResponse.code,
    msg = baseResponse.msg,
)
