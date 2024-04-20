package com.example.data.model

import com.example.data.base.BaseResponse
import com.example.domain.model.ChatsPreviewModel
import com.example.domain.model.ChatsPreviewModelResult


data class ChatsPreviewResponse(
    val baseResponse: BaseResponse,
    val listOfChatsPreview: List<ChatPreviewModel>,
)

data class ChatPreviewModel(
    val id: String,
    val title: String,
    val lastMessage: String,
)

internal fun ChatsPreviewResponse.mapToDomainModel(): ChatsPreviewModelResult  {
    val newList = listOfChatsPreview.map { chatPreviewModel ->
        ChatsPreviewModel(
            id = chatPreviewModel.id,
            title = chatPreviewModel.title,
            lastMessage = chatPreviewModel.lastMessage
        )
    }

    return ChatsPreviewModelResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        list = newList
    )
}