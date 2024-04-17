package com.example.papper.features.chat.chat.model

data class Message(
    val text: String,
    val from: MessageSender,
)

sealed class MessageSender {
    object Bot : MessageSender()
    object User : MessageSender()
}