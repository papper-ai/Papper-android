package com.example.domain.model.chat

data class SendMessageResult (
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
)