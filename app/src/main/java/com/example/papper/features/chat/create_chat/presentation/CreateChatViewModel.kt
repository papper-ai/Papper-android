package com.example.papper.features.chat.create_chat.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.storage.GetStorageByIdUseCase
import com.example.papper.features.storage.storage.model.mapToPresentationModel
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
class CreateChatViewModel @Inject constructor(
    private val getStorageByIdUseCase: GetStorageByIdUseCase
) : ViewModel(), ContainerHost<CreateChatState, CreateChatSideEffects> {

    override val container = container<CreateChatState, CreateChatSideEffects>(CreateChatState())

    val createChatScreenState = mutableStateOf<CreateChatScreenState>(CreateChatScreenState.TypingTitle)
    val chooseStorageScreenState = mutableStateOf<ChooseStorageScreenState>(ChooseStorageScreenState.ChooseVariable)
    val createBtn = mutableStateOf<Boolean>(false)

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
    }

    fun toChooseStorage() = intent {
        postSideEffect(CreateChatSideEffects.ShowChooseStorageScreen)
    }

    fun toListOfFiles(id: String) = intent {
        postSideEffect(CreateChatSideEffects.ShowLoading)
        val result = withContext(AppDispatchers.io) {
            getStorageByIdUseCase.execute(id = id).mapToPresentationModel()
        }
        if (result.isSuccess) {
            reduce {
                state.copy(listOfFiles = result.listOfFiles)
            }
            createBtn.value = state.listOfFiles?.isNotEmpty() == true
            postSideEffect(CreateChatSideEffects.ShowListOfFilesScreen)
        }
        else {

        }

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

    fun getStorage() = intent {
        postSideEffect(CreateChatSideEffects.NavigateToStoragesScreen)
    }

}