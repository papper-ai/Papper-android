package com.example.papper.features.chat.chats.model

import com.example.domain.model.ChatsPreviewModel

data class ChatDescription(
    val id: String,
    val title: String,
    val lastMsg: String,
)

typealias ChatsPreviewList = List<ChatsPreviewModel>
internal fun ChatsPreviewModel.mapToPresentationModel() = ChatDescription(
    id = id,
    title = title,
    lastMsg = lastMessage,
)

internal fun ChatsPreviewList.mapToPresentationModel() =
    map { chatsPreviewModel ->
        chatsPreviewModel.mapToPresentationModel()
    }

