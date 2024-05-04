package com.example.data.model.chat

import com.example.data.base.BaseResponse
import com.example.domain.model.chat.CreateChatResult
import com.google.gson.annotations.SerializedName

data class CreateChatResponse(
    val id: String,
)

data class CreateChatBody(
    @SerializedName("vault_id")
    val id: String,
    @SerializedName("name")
    val title: String,
)

data class CreateChatResponseResult(
    val baseResponse: BaseResponse,
    val id: String,
)

internal fun CreateChatResponseResult.mapToDomainModel(): CreateChatResult =
    CreateChatResult(
        isSuccess = baseResponse.isSuccess,
        msg = baseResponse.msg,
        id = id
    )