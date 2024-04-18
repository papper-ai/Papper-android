package com.example.papper.features.chat.chat.presentation

import com.example.papper.features.chat.chat.model.Message

data class ChatState(
    val title: String = "",
    val message: String = "",
    val listOfMessages: List<Message> = emptyList(),
)

sealed class ChatScreenState {
    object Loading : ChatScreenState()
    object Error : ChatScreenState()
    object Success : ChatScreenState()
}

sealed class SuccessState {
    object EmptyChat : SuccessState()
    object NotEmptyChat : SuccessState()
}