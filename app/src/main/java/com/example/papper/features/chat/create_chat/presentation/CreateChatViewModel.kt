package com.example.papper.features.chat.create_chat.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.papper.base.BaseViewModel
import com.example.papper.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class CreateChatViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
) : BaseViewModel<CreateChatSideEffects>(appState) {

    val createChatScreenState = mutableStateOf<CreateChatScreenState>(CreateChatScreenState.TypingTitle)
    val createBtn = mutableStateOf<Boolean>(false)

    fun updateTitle(title: String) = intent {
        reduce {
            state.value = state.value.copy(createChatState = state.value.createChatState.copy(title = title))
            state
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
                state.value = state.value.copy(createChatState = state.value.createChatState.copy(listOfFiles = emptyList()))
                state
            }
            else {
                state.value = state.value.copy(createChatState = state.value.createChatState.copy(listOfFiles = null))
                state
            }
        }
        Log.d("Test", "skipClicked: state: ${(state.value.createChatState.listOfFiles != null)}")
    }

}