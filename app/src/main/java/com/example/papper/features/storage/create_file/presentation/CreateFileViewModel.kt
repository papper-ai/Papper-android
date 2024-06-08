package com.example.papper.features.storage.create_file.presentation

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.papper.features.storage.create_storage.view.attach_files.CreateStorageBtnStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateFileViewModel @Inject constructor(

): ViewModel(), ContainerHost<CreateFileState, CreateFileSideEffects> {

    override val container = container<CreateFileState, CreateFileSideEffects>(CreateFileState())
    val createFileScreenState = mutableStateOf<CreateFileScreenState>(CreateFileScreenState.TypingTitle)
    val createStorageBtnStatus = mutableStateOf<CreateStorageBtnStatus>(CreateStorageBtnStatus(isLoading = false, isEnable = true))

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
    }

    fun attachPhotoFromCamera(photo: Bitmap) = intent {
        reduce {
            state.copy(listOfPhotos = state.listOfPhotos.plus(photo))
        }
    }

    fun toAttachPhotos() = intent {
        postSideEffect(CreateFileSideEffects.ShowAttachPhotos)
    }

    fun toConfirmCreating() = intent {
        postSideEffect(CreateFileSideEffects.ShowConfirmCreating)
    }



}