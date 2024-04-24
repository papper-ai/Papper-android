package com.example.papper.features.chat.create_chat.presentation

import com.example.papper.features.storage.storage.model.FilePresentationModel

data class CreateChatState(
    val title: String = "",
    val listOfFiles: List<FilePresentationModel>? = null
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