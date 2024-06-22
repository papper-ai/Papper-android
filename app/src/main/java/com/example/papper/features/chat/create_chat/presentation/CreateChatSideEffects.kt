package com.example.papper.features.chat.create_chat.presentation

sealed class CreateChatSideEffects {
    object ShowLoading: CreateChatSideEffects()
    object ShowChooseStorageScreen: CreateChatSideEffects()
    object ShowListOfFilesScreen: CreateChatSideEffects()
    data class NavigateToChatScreen(val id: String): CreateChatSideEffects()
    object NavigateToStoragesScreen: CreateChatSideEffects()
    object ShowFetchStorageError: CreateChatSideEffects()
    object ShowCreateChatErrorToast: CreateChatSideEffects()
    object ShowNetworkConnectionError: CreateChatSideEffects()
    object NavigateToCreateStorageScreen: CreateChatSideEffects()
}