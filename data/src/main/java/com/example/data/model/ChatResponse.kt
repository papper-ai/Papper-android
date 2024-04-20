package com.example.data.model

import com.example.data.base.BaseResponse
import com.example.domain.model.ChatModel
import com.example.domain.model.ChatModelResult

data class ChatResponse(
    val baseResponse: BaseResponse,
    val id: String,
    val title: String,
    val listOfMessages: List<Message>,
    val storageId: String,
)

data class Message(
    val text: String,
    val from: String,
)

internal fun ChatResponse.mapToDomainModel() = ChatModelResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        chatModel = ChatModel(
            id = id,
            title = title,
            listOfMessages = listOfMessages.map { message ->
                com.example.domain.model.Message(
                    text = message.text,
                    from = message.from,
                )
            },
            storageId = storageId
        )
    )
