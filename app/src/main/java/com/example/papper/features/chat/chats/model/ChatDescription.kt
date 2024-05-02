package com.example.papper.features.chat.chats.model

import com.example.domain.model.chat.ChatsPreviewModelResult

data class ChatDescriptionResult(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val list: List<ChatDescription>
)

data class ChatDescription(
    val id: String,
    val title: String,
//    val lastMsg: String,
)

internal fun ChatsPreviewModelResult.mapToPresentationModel() : ChatDescriptionResult {
    val newList = list.map { chatPreviewModel ->
        ChatDescription(
            id = chatPreviewModel.id,
            title = chatPreviewModel.title,
//            lastMsg = chatPreviewModel.lastMessage
        )
    }

    return ChatDescriptionResult(
        isSuccess = isSuccess,
        code = code,
        msg = msg,
        list = newList,
    )
}