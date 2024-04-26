package com.example.papper.features.chat.chat.presentation

import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.storage.storage.model.FilePresentationModel

data class ChatState(
    val title: String = "",
    val message: String = "",
    val file: FilePresentationModel? = null,
    val listOfMessages: List<Message> = emptyList(),
    val storageId: String = "",
)

sealed class ChatScreenState {
    object Loading : ChatScreenState()
    object Error : ChatScreenState()
    object Success : ChatScreenState()
}

sealed class SuccessState {
    object EmptyChat : SuccessState()
    object NotEmptyChat : SuccessState()
}