package com.example.papper.features.storage.create_storage.presentation

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
class CreateStorageViewModel @Inject constructor() : ViewModel(), ContainerHost<CreateStorageState, CreateStorageSideEffects> {

    override val container = container<CreateStorageState, CreateStorageSideEffects>(
        CreateStorageState()
    )

    val createStorageScreenState = mutableStateOf<CreateStorageScreenState>(CreateStorageScreenState.TypingTitle)

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
    }

    fun toAttachFiles() = intent {
        postSideEffect(CreateStorageSideEffects.ShowAttachFilesScreen)
    }

//    fun updateFiles() = intent {
//        reduce {
//            state.value = state.value.copy(createStorageState = state.value.createStorageState.copy(title = state.value.createStorageState.title))
//            state
//        }
//    }

}