package com.example.data.model.chat

import com.example.data.base.BaseResponse
import com.example.domain.model.chat.SendMessageResult

data class SendMessageResponse(
    val baseResponse: BaseResponse,
)

internal fun SendMessageResponse.mapToDomainModel() = SendMessageResult(
    isSuccess = baseResponse.isSuccess,
    code = baseResponse.code,
    msg = baseResponse.msg,
)
