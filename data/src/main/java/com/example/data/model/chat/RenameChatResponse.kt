package com.example.data.model.chat

import com.google.gson.annotations.SerializedName

data class RenameChatBody(
    @SerializedName("chat_id")
    val chatId: String,
    val name: String
)