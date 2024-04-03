package com.example.papper.features.chat.chats.presentation

import com.example.papper.features.chat.chats.model.ChatDescription

data class ChatsState(
    val listOfChats: List<ChatDescription> = emptyList()
)