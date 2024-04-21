package com.example.domain.model

data class SendMessageResult (
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
)