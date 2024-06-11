package com.example.papper.features.storage.create_file.presentation

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.papper.features.storage.create_file.model.AttachPhotoModel
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

    fun attachPhotoFromCamera(bitmap: Bitmap) = intent {
        val photo = AttachPhotoModel(
            id = state.listOfPhotos.size+1,
            image = bitmap,
        )

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

    fun addPhoto(bitmap: Bitmap) = intent {
        val photo = AttachPhotoModel(
            id = state.listOfPhotos.size+1,
            image = bitmap,
        )

        reduce {
            state.copy(listOfPhotos = state.listOfPhotos.plus(photo))
        }
    }

    fun deletePhoto(photo: AttachPhotoModel) = intent {
        val result = mutableListOf<AttachPhotoModel>()
        var index = 1
        for (item in state.listOfPhotos.minus(photo)) {
            result.add(
                AttachPhotoModel(
                    id = index,
                    image = item.image,
                )
            )
            index++
        }

        reduce {
            state.copy(listOfPhotos = result)
        }
    }

    fun replacePhoto(oldPhoto: AttachPhotoModel, newPhoto: AttachPhotoModel) = intent {
        val result: MutableList<AttachPhotoModel> = state.listOfPhotos.toMutableList()
        result[result.indexOf(oldPhoto)] = newPhoto

        reduce {
            state.copy(listOfPhotos = result)
        }
    }

}