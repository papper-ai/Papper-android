package com.example.papper.features.storage.create_storage.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.storage.CreateStorageUseCase
import com.example.papper.features.storage.create_storage.view.attach_files.CreateStorageBtnStatus
import com.example.papper.navigation.Screens
import com.example.papper.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
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

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
    }

    fun toAttachFiles() = intent {
        postSideEffect(CreateStorageSideEffects.ShowAttachFilesScreen)
    }

    fun createStorage(lastDestination: String) = intent {
        createStorageBtnStatus.value = CreateStorageBtnStatus(isLoading = true, isEnable = false)
        val result = withContext(AppDispatchers.io) {
            createStorageUseCase.execute(title = state.title, list = state.listOfFiles)
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

//    fun updateFiles() = intent {
//        reduce {
//            state.value = state.value.copy(createStorageState = state.value.createStorageState.copy(title = state.value.createStorageState.title))
//            state
//        }
//    }

}