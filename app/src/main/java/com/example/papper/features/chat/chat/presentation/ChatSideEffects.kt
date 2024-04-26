package com.example.papper.features.chat.chat.presentation

sealed class ChatSideEffects {
    object ShowLoading : ChatSideEffects()
    object ShowSuccess : ChatSideEffects()
    object ShowError : ChatSideEffects()
    object ShowErrorToast : ChatSideEffects()
    data class NavigateToStorageScreen(val storageId: String): ChatSideEffects()
}