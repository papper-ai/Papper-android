package com.example.papper.features.chat.create_chat.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateChatViewModel @Inject constructor() : ViewModel(), ContainerHost<CreateChatState, CreateChatSideEffects> {

    override val container = container<CreateChatState, CreateChatSideEffects>(CreateChatState())

    val createChatScreenState = mutableStateOf<CreateChatScreenState>(CreateChatScreenState.TypingTitle)
    val createBtn = mutableStateOf<Boolean>(false)

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
    }

    fun toChooseStorage() = intent {
        postSideEffect(CreateChatSideEffects.ShowChooseStorageScreen)
    }

    fun toListOfFiles() = intent {
        postSideEffect(CreateChatSideEffects.ShowListOfFilesScreen)
    }

    fun skipClicked(status: Boolean) = intent {
        reduce {
            createBtn.value = status
            if (status) {
                state.copy(listOfFiles = emptyList())
            }
            else {
                state.copy(listOfFiles = null)
            }
        }
    }

}