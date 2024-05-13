package com.example.papper.features.chat.create_chat.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.chat.CreateChatUseCase
import com.example.domain.usecases.storage.GetStorageByIdUseCase
import com.example.papper.features.storage.storage.model.mapToPresentationModel
import com.example.papper.utils.AppDispatchers
import com.example.papper.utils.CheckNetworkStatus
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
    private val checkNetworkStatus: CheckNetworkStatus,
    private val getStorageByIdUseCase: GetStorageByIdUseCase,
    private val createChatUseCase: CreateChatUseCase,
) : ViewModel(), ContainerHost<CreateChatState, CreateChatSideEffects> {

    override val container = container<CreateChatState, CreateChatSideEffects>(CreateChatState())

    val createChatScreenState = mutableStateOf<CreateChatScreenState>(CreateChatScreenState.TypingTitle)
    val chooseStorageScreenState = mutableStateOf<ChooseStorageScreenState>(ChooseStorageScreenState.ChooseVariable)
    val createBtn = mutableStateOf<Boolean>(false)
    val createBtnLoading = mutableStateOf<Boolean>(false)

    fun createChat() = intent {
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
                createBtnLoading.value = true
                createBtn.value = false
                val result = withContext(AppDispatchers.io) {
                    createChatUseCase.execute(id = state.vaultId.orEmpty(), title = state.title)
                }

                if (result.isSuccess) {
                    postSideEffect(CreateChatSideEffects.NavigateToChatScreen(id = result.id))
                } else {
                    postSideEffect(CreateChatSideEffects.ShowCreateChatErrorToast)
                }
                createBtnLoading.value = false
                createBtn.value = state.listOfFiles?.isNotEmpty() == true
            },
            onFail = {
                postSideEffect(CreateChatSideEffects.ShowNetworkConnectionError)
            },
        )
    }

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
    }

    fun toChooseStorage() = intent {
        postSideEffect(CreateChatSideEffects.ShowChooseStorageScreen)
    }

    fun toListOfFiles(id: String) = intent {
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
                postSideEffect(CreateChatSideEffects.ShowLoading)
                val result = withContext(AppDispatchers.io) {
                    getStorageByIdUseCase.execute(id = id).mapToPresentationModel()
                }
                if (result.isSuccess) {
                    reduce {
                        state.copy(listOfFiles = result.listOfFiles, vaultId = id)
                    }
                    createBtn.value = state.listOfFiles?.isNotEmpty() == true
                    postSideEffect(CreateChatSideEffects.ShowListOfFilesScreen)
                }
                else {
                    postSideEffect(CreateChatSideEffects.ShowFetchStorageError)
                }
            },
            onFail = {
                postSideEffect(CreateChatSideEffects.ShowNetworkConnectionError)
            },
        )
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