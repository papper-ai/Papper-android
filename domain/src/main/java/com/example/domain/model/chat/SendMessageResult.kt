package com.example.domain.model.chat

data class SendMessageResult (
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val content: String,
    val msgTraceback: List<MsgTraceback>,
)

data class MsgTraceback(
    val documentId: String,
    val information: String
)