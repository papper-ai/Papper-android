package com.example.papper.features.storage.create_storage.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.storage.CreateStorageUseCase
import com.example.papper.features.storage.create_storage.view.attach_files.CreateStorageBtnStatus
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.navigation.Screens
import com.example.papper.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateStorageViewModel @Inject constructor(
    private val createStorageUseCase: CreateStorageUseCase,
) : ViewModel(), ContainerHost<CreateStorageState, CreateStorageSideEffects> {

    override val container = container<CreateStorageState, CreateStorageSideEffects>(
        CreateStorageState()
    )

    val createStorageScreenState = mutableStateOf<CreateStorageScreenState>(CreateStorageScreenState.TypingTitle)
    val createStorageBtnStatus = mutableStateOf<CreateStorageBtnStatus>(CreateStorageBtnStatus(isLoading = false, isEnable = true))
    val chooseStorageType = mutableStateOf<StorageType?>(null)

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
    }

    fun vectorBtnClick() = intent {
        chooseStorageType.value = StorageType.vector
        reduce {
            state.copy(storageType = StorageType.vector.type)
        }
    }

    fun graphBtnClick() = intent {
        chooseStorageType.value = StorageType.graph
        reduce {
            state.copy(storageType = StorageType.graph.type)
        }
    }

    fun toChooseTypeStorage() = intent {
        postSideEffect(CreateStorageSideEffects.ShowChooseStorageType)
    }

    fun toAttachFiles() = intent {
        postSideEffect(CreateStorageSideEffects.ShowAttachFilesScreen)
    }

    fun createStorage(lastDestination: String) = intent {
        createStorageBtnStatus.value = CreateStorageBtnStatus(isLoading = true, isEnable = false)
        val result = withContext(AppDispatchers.io) {
            createStorageUseCase.execute(title = state.title, list = state.listOfFiles.toList())
        }
        if (result.isSuccess) {
            if (lastDestination == Screens.CreateChatScreen.route) {
                postSideEffect(CreateStorageSideEffects.NavigateToCreateChatScreen(id = result.id))
            }
            else if (lastDestination == Screens.StoragesScreen.route) {
                postSideEffect(CreateStorageSideEffects.NavigateToStorageScreen(id = result.id))
            }
        }
        else {
            createStorageBtnStatus.value = CreateStorageBtnStatus(isLoading = false, isEnable = true)
            postSideEffect(CreateStorageSideEffects.ShowErrorToast)
        }
    }

    fun addFile(file: File) = intent {
        reduce {
            state.copy(listOfFiles = state.listOfFiles.plus(file))
        }
    }

    fun deleteFile(file: File) = intent {
        reduce {
            state.copy(listOfFiles = state.listOfFiles.minus(file))
        }
    }

//    fun updateFiles() = intent {
//        reduce {
//            state.value = state.value.copy(createStorageState = state.value.createStorageState.copy(title = state.value.createStorageState.title))
//            state
//        }
//    }

}