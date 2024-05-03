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
    val isArchived: Boolean,
)

data class Message(
    val text: String,
    val from: String,
    val traceBack: List<TraceBack>?
)

data class TraceBack(
    val documentId: String,
    val information: String?,
)