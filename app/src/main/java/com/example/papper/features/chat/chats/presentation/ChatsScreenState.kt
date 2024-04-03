package com.example.papper.features.chat.chats.presentation

sealed class ChatsScreenState {
    object  Loading: ChatsScreenState()
    object  Success: ChatsScreenState()
    object  Error: ChatsScreenState()
}