package com.example.papper.features.chat.chat.presentation

data class ChatState(
    val title: String = "",
    val message: String = "",
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