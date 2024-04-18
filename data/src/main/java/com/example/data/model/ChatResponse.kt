package com.example.data.model

import com.example.domain.model.ChatModel

data class ChatResponse(
    val id: String,
    val title: String,
    val listOfMessages: List<Message>,
    val storageId: String,
)

data class Message(
    val text: String,
    val from: String,
)

internal fun ChatResponse.mapToDomainModel() = ChatModel(
    id = id,
    title = title,
    listOfMessages = listOfMessages.map { message ->
        com.example.domain.model.Message(
            text = message.text,
            from = message.from
        )
    },
    storageId = storageId
)