package com.example.papper.features.chat.chat.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.chat.GetChatByIdUseCase
import com.example.domain.usecases.chat.SendMessageUseCase
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.chat.chat.model.MessageSender
import com.example.papper.features.chat.chat.model.mapToPresentationModel
import com.example.papper.features.chat.chat.view.success_data.bottom_bar.MessageStatus
import com.example.papper.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
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
    private val sendMessageUseCase: SendMessageUseCase,
) : ViewModel(), ContainerHost<ChatState, ChatSideEffects> {

    override val container: Container<ChatState, ChatSideEffects> = container<ChatState, ChatSideEffects>(ChatState())

    var id: String? = null
    val chatScreenState = mutableStateOf<ChatScreenState>(ChatScreenState.Loading)
    val successState = mutableStateOf<SuccessState>(SuccessState.EmptyChat)
    val isSendingMessage = mutableStateOf<MessageStatus>(MessageStatus(false, false))

    init {
        loadData()
    }

    fun loadData() = intent {
        postSideEffect(ChatSideEffects.ShowLoading)
        val result = withContext(AppDispatchers.io) {
            id?.let { getChatByIdUseCase.execute(it).mapToPresentationModel() }
        }
        if (result != null) {
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
                postSideEffect(ChatSideEffects.ShowSuccess)
            }
            else {
                postSideEffect(ChatSideEffects.ShowError)
            }
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
        isSendingMessage.value = MessageStatus(isSendingMsg = true, isSuccess = false)

        val result = withContext(AppDispatchers.io) {
            sendMessageUseCase.execute(message = text)
        }

        if (result.isSuccess) {
            if (state.listOfMessages.isEmpty()) {
                successState.value = SuccessState.NotEmptyChat
            }
            reduce {
                state.copy(listOfMessages = state.listOfMessages.plus(Message(text = text, from = MessageSender.User)), message = "")
            }
            isSendingMessage.value = MessageStatus(isSendingMsg = false, isSuccess = true)
        }
        else {
            isSendingMessage.value = MessageStatus(isSendingMsg = false, isSuccess = false)
            postSideEffect(ChatSideEffects.ShowErrorToast)
        }

        for (item in state.listOfMessages) {
            Log.d("test", "sendMessage: ${item.text}")
        }
    }

}