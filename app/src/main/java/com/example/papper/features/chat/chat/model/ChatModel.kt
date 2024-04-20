package com.example.papper.features.chat.chat.model


import com.example.domain.model.ChatModel

data class PresentationChatModel(
    val id: String,
    val title: String,
    val listOfMessages: List<Message>,
    val storageId: String,
)

data class Message(
    val text: String,
    val from: MessageSender,
)

sealed class MessageSender {
    object Bot : MessageSender()
    object User : MessageSender()
}

internal fun ChatModel.mapToPresentationModel() : PresentationChatModel =
    PresentationChatModel(
        id = id,
        title = title,
        listOfMessages = listOfMessages.map { message ->
            Message(
                text = message.text,
                from = if (message.from == "Bot") MessageSender.Bot else MessageSender.User,
            )
        },
        storageId = storageId
    )