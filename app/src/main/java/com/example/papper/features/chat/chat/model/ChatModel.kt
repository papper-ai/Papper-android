package com.example.papper.features.chat.chat.model

import com.example.domain.model.chat.ChatModelResult

data class PresentationChatModelResult(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
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

internal fun ChatModelResult.mapToPresentationModel() : PresentationChatModelResult =
    PresentationChatModelResult(
        isSuccess = isSuccess,
        code = code,
        msg = msg,
        id = chatModel.id,
        title = chatModel.title,
        listOfMessages = chatModel.listOfMessages.map { message ->
            Message(
                text = message.text,
                from = if (message.from == "Bot") MessageSender.Bot else MessageSender.User
            )
        },
        storageId = chatModel.storageId
    )