package com.example.domain.model.chat

data class CreateChatResult(
    val isSuccess: Boolean,
    val msg: String,
    val id: String,
)