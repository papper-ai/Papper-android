package com.example.domain.model.chat

data class ChatModelResult(
    val isSuccess: Boolean,
    val code: String,
    val msg: String,
    val chatModel: ChatModel,
)

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