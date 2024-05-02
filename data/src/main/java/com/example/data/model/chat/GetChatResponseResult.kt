package com.example.data.model.chat

import com.example.data.base.BaseResponse
import com.example.domain.model.chat.ChatModel
import com.example.domain.model.chat.ChatModelResult
import com.google.gson.annotations.SerializedName

data class GetChatResponse(
    val id: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("vault_id")
    val vaultId: String,
    @SerializedName("is_archived")
    val isArchived: Boolean,
    @SerializedName("chat_history")
    val chatHistory: ChatHistory
)

data class ChatHistory(
    @SerializedName("chat_id")
    val chatId: String,
    val history: List<HistoryItem>
)

data class HistoryItem(
    val role: String,
    val content: String,
    val traceback: List<TracebackItem>?
)

data class TracebackItem(
    @SerializedName("document_id")
    val documentId: String,
    val information: String?
)

data class GetChatResponseResult(
    val baseResponse: BaseResponse,
    val id: String,
    val title: String,
    val listOfMessages: List<HistoryItem>,
    val storageId: String,
)

internal fun GetChatResponseResult.mapToDomainModel() = ChatModelResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        chatModel = ChatModel(
            id = id,
            title = title,
            listOfMessages = listOfMessages.map { message ->
                com.example.domain.model.chat.Message(
                    text = message.content,
                    from = message.role,
                    traceBack = message.traceback?.map { tracebackItem ->
                        com.example.domain.model.chat.TraceBack(
                            documentId = tracebackItem.documentId,
                            information = tracebackItem.information,
                        )
                    }
                )
            },
            storageId = storageId
        )
    )
