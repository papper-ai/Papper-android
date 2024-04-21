package com.example.papper.features.chat.create_chat.presentation

import java.io.File

data class CreateChatState(
    val title: String = "",
    val listOfFiles: List<File>? = null
)

sealed class CreateChatScreenState {
    object TypingTitle: CreateChatScreenState()
    object ChooseStorage: CreateChatScreenState()

}

sealed class ChooseStorageScreenState {
    object Loading: ChooseStorageScreenState()
    object ChooseVariable: ChooseStorageScreenState()
    object ListOfFiles: ChooseStorageScreenState()
}