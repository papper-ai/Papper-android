package com.example.papper.features.chat.chats.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.CheckApiUseCase
import com.example.domain.usecases.chat.GetAllChatsPreviewUseCase
import com.example.papper.features.chat.chats.model.mapToPresentationModel
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
class ChatsViewModel @Inject constructor(
    private val checkNetworkStatus: CheckNetworkStatus,
    private val getAllChatsUseCase: GetAllChatsPreviewUseCase,
    private val checkApiUseCase: CheckApiUseCase,
) : ViewModel(), ContainerHost<ChatsState, ChatsSideEffects> {

    override val container = container<ChatsState, ChatsSideEffects>(ChatsState())

    val chatsScreenState = mutableStateOf<ChatsScreenState>(ChatsScreenState.Loading)

    init {
        loadData()
        //checkApi()
    }

    fun loadData() = intent {
        postSideEffect(ChatsSideEffects.ShowLoading)
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
                val result = withContext(AppDispatchers.io) {
                    getAllChatsUseCase.execute().mapToPresentationModel()
                }
                if (result.isSuccess) {
                    reduce {
                        state.copy(listOfChats = result.list)
                    }
                    postSideEffect(ChatsSideEffects.ShowSuccess)
                } else {
                    postSideEffect(ChatsSideEffects.ShowError)
                }
            },
            onFail = {
                postSideEffect(ChatsSideEffects.ShowNetworkConnectionError)
            }
        )

    }

    fun renameChat(id: String, title: String) = intent {
        val list = state.listOfChats.toMutableList()
        list.find { it.id == id }?.title = title
        reduce {
            state.copy(listOfChats = list)
        }
    }

    fun deleteChat(id: String) = intent {
        val list = state.listOfChats.toMutableList()
        list.removeIf { it.id == id }
        reduce {
            state.copy(listOfChats = list)
        }
    }

    private fun checkApi() = intent {
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
                withContext(AppDispatchers.io) {
                    checkApiUseCase.execute()
                }
            },
            onFail = {
                Log.d("Test", "checkApi: Отсутствует подключение к интернету")
            }
        )
    }

    fun navigateToCreateChatScreen() = intent {
        postSideEffect(ChatsSideEffects.NavigateToCreateChatScreen)
    }

    fun navigateToChatScreen(id: String) = intent {
        postSideEffect(ChatsSideEffects.NavigateToChatScreen(id))
    }

}