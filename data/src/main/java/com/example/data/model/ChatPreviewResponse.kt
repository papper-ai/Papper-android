package com.example.data.model

import com.example.domain.model.ChatsPreviewModel

data class ChatsPreviewResponse(
    val listOfChatsPreview: List<ChatPreviewModel>
)

data class ChatPreviewModel(
    val id: String,
    val title: String,
    val lastMessage: String,
)

internal fun ChatsPreviewResponse.mapToDomainModel(): List<ChatsPreviewModel> =
    listOfChatsPreview.map { chatPreviewModel ->
        ChatsPreviewModel(
            id = chatPreviewModel.id,
            title = chatPreviewModel.title,
            lastMessage = chatPreviewModel.lastMessage
        )
    }