package com.example.data.model.chat

import com.example.data.base.BaseResponse
import com.example.domain.model.chat.ChatsPreviewModel
import com.example.domain.model.chat.ChatsPreviewModelResult
import com.google.gson.annotations.SerializedName

data class ChatPreviewItem(
    val id: String,
    @SerializedName("name")
    val title: String,
)


data class ChatsPreviewResponseResult(
    val baseResponse: BaseResponse,
    val listOfChatsPreview: List<ChatPreviewItem>,
)

internal fun ChatsPreviewResponseResult.mapToDomainModel(): ChatsPreviewModelResult {
    val newList = listOfChatsPreview.map { chatPreviewModel ->
        ChatsPreviewModel(
            id = chatPreviewModel.id,
            title = chatPreviewModel.title,
//            lastMessage = chatPreviewModel.lastMessage
        )
    }

    return ChatsPreviewModelResult(
        isSuccess = baseResponse.isSuccess,
        code = baseResponse.code,
        msg = baseResponse.msg,
        list = newList
    )
}