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
    val isArchived: Boolean,
)

data class Message(
    val text: String,
    val from: MessageSender,
    val traceBack: List<TraceBack>?
)

data class TraceBack(
    val documentId: String,
    val information: String?,
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
                from = if (message.from == "ai") MessageSender.Bot else MessageSender.User,
                traceBack = message.traceBack?.map { traceBack ->
                    TraceBack(
                        documentId = traceBack.documentId,
                        information = traceBack.information,
                    )
                }
            )
        },
        storageId = chatModel.storageId,
        isArchived = chatModel.isArchived
    )