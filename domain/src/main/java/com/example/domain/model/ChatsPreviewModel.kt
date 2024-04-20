package com.example.domain.model

data class ChatsPreviewModelResult (
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val list: List<ChatsPreviewModel>
)

data class ChatsPreviewModel(
    val id: String,
    val title: String,
    val lastMessage: String,
)