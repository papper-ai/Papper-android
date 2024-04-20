package com.example.papper.features.chat.chats.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.datasource.ChatRemoteDataSource
import com.example.data.repository.ChatRepositoryImpl
import com.example.domain.usecases.chat.GetAllChatsPreviewUseCase
import com.example.papper.features.chat.chats.model.mapToPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getAllChatsUseCase: GetAllChatsPreviewUseCase,
) : ViewModel(), ContainerHost<ChatsState, ChatsSideEffects> {

    override val container = container<ChatsState, ChatsSideEffects>(ChatsState())

    val chatsScreenState = mutableStateOf<ChatsScreenState>(ChatsScreenState.Loading)

    init {
        loadData()
    }

    private fun loadData() = intent {
        postSideEffect(ChatsSideEffects.ShowLoading)
        viewModelScope.launch {
            val result = getAllChatsUseCase.execute().mapToPresentationModel()
            reduce {
                state.copy(listOfChats = result)
            }
            //postSideEffect(ChatsSideEffects.ShowError)
            postSideEffect(ChatsSideEffects.ShowSuccess)
        }
    }
}