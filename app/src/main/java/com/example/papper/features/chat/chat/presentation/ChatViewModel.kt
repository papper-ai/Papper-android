package com.example.papper.features.chat.chat.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.data.datasource.ChatRemoteDataSource
import com.example.data.repository.ChatRepositoryImpl
import com.example.domain.usecases.chat.GetChatByIdUseCase
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.chat.chat.model.MessageSender
import com.example.papper.features.chat.chat.model.mapToPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatByIdUseCase: GetChatByIdUseCase,
) : ViewModel(), ContainerHost<ChatState, ChatSideEffects> {

    override val container: Container<ChatState, ChatSideEffects> = container<ChatState, ChatSideEffects>(ChatState())

    var id: String = ""
    val chatScreenState = mutableStateOf<ChatScreenState>(ChatScreenState.Loading)
    val successState = mutableStateOf<SuccessState>(SuccessState.EmptyChat)

    init {
        loadData()
    }

    fun loadData() = intent {
        postSideEffect(ChatSideEffects.ShowLoading)
        val result = getChatByIdUseCase.execute(id).mapToPresentationModel()
        if (result.isSuccess) {
            reduce {
                state.copy(
                    title = result.title,
                    listOfMessages = result.listOfMessages
                )
            }
            if (state.listOfMessages.isNotEmpty()) {
                successState.value = SuccessState.NotEmptyChat
            }
            else {
                successState.value = SuccessState.EmptyChat
            }
            Log.d("Test", "loadData: попал")
            postSideEffect(ChatSideEffects.ShowSuccess)
        }
        else {
            postSideEffect(ChatSideEffects.ShowError)
        }
    }

    fun updateMessage(message: String) = intent {
        reduce {
            state.copy(message = message)
        }
    }

    fun sendMessage(text: String) = intent {
        if (state.listOfMessages.isEmpty()) {
            successState.value = SuccessState.NotEmptyChat
        }
        reduce {
            state.copy(listOfMessages = state.listOfMessages.plus(Message(text = text, from = MessageSender.User)))
        }

        for (item in state.listOfMessages) {
            Log.d("test", "sendMessage: ${item.text}")
        }
    }

}