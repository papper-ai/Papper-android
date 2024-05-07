package com.example.data.model.chat

import com.example.data.base.BaseResponse
import com.example.domain.model.chat.SendMessageResult
import com.google.gson.annotations.SerializedName

data class SendMessageBody(
    @SerializedName("vault_id")
    val vaultId: String,
    @SerializedName("chat_id")
    val chatId: String,
    val query: String,
)

data class SendMessageResponse(
    val content: String,
    val traceback: List<Traceback>,
)

data class Traceback(
    @SerializedName("document_id")
    val documentId: String,
    val information: String
)

data class SendMessageResponseResult(
    val baseResponse: BaseResponse,
    val content: String,
    val traceback: List<Traceback>,
)

internal fun SendMessageResponseResult.mapToDomainModel() = SendMessageResult(
    isSuccess = baseResponse.isSuccess,
    code = baseResponse.code,
    msg = baseResponse.msg,
    content = content,
    msgTraceback = traceback.map {
        com.example.domain.model.chat.MsgTraceback(
            documentId = it.documentId,
            information = it.information
        )
    }
)
