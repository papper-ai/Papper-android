package com.example.domain.model
data class ChatModel(
    val id: String,
    val title: String,
    val listOfMessages: List<Message>,
    val storageId: String,
)

data class Message(
    val text: String,
    val from: String,
)