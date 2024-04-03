package com.example.papper.features.chat.chats.presentation

sealed class ChatsSideEffects {
    object ShowLoading: ChatsSideEffects()
    object ShowError: ChatsSideEffects()
    object ShowSuccess: ChatsSideEffects()
}