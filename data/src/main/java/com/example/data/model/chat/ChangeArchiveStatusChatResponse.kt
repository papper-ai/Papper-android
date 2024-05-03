package com.example.data.model.chat

import com.example.data.base.BaseResponse
import com.google.gson.annotations.SerializedName

data class ChangeArchiveStatusChatResponseResult(
    val baseResponse: BaseResponse
)

data class ChangeArchiveStatusChatBody(
    @SerializedName("chat_id")
    val chatId: String,
    @SerializedName("archive_action")
    val archiveAction: String,
)