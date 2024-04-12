package com.example.papper.features.archive.presentation

import com.example.papper.features.chat.chats.model.ChatDescription

data class ArchivesState(
    val listOfChats: List<ChatDescription> = emptyList()
)