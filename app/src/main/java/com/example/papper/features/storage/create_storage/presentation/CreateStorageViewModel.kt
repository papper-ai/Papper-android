package com.example.papper.features.storage.create_storage.presentation

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
class CreateStorageViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>
) : BaseViewModel<CreateStorageSideEffects>(appState) {

    val createStorageScreenState = mutableStateOf<CreateStorageScreenState>(CreateStorageScreenState.TypingTitle)

    fun updateTitle(title: String) = intent {
        reduce {
            state.value = state.value.copy(createStorageState = state.value.createStorageState.copy(title = title))
            state
        }
    }

    fun toAttachFiles() = intent {
        postSideEffect(CreateStorageSideEffects.ShowAttachFilesScreen)
    }

    fun updateFiles() = intent {
        reduce {
            state.value = state.value.copy(createStorageState = state.value.createStorageState.copy(title = state.value.createStorageState.title))
            state
        }
    }

}