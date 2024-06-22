package com.example.papper.features.chat.chats.presentation

sealed class ChatsSideEffects {
    object ShowLoading: ChatsSideEffects()
    object ShowError: ChatsSideEffects()
    object ShowNetworkConnectionError: ChatsSideEffects()
    object ShowSuccess: ChatsSideEffects()
    object NavigateToCreateChatScreen: ChatsSideEffects()
    data class NavigateToChatScreen(val id: String): ChatsSideEffects()
    object NavigateToArchivesScreen: ChatsSideEffects()
    object NavigateToProfileScreen: ChatsSideEffects()
    object NavigateToStoragesScreen: ChatsSideEffects()
}