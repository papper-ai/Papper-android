package com.example.papper.features.chat.create_chat.presentation

sealed class CreateChatSideEffects {
    object ShowLoading: CreateChatSideEffects()
    object ShowChooseStorageScreen: CreateChatSideEffects()
    object ShowListOfFilesScreen: CreateChatSideEffects()
    object NavigateToStoragesScreen: CreateChatSideEffects()
}