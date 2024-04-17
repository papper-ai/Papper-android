package com.example.papper.features.chat.chat.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.papper.base.BaseViewModel
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.chat.chat.model.MessageSender
import com.example.papper.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
) : BaseViewModel<ChatSideEffects>(appState) {

    val chatScreenState = mutableStateOf<ChatScreenState>(ChatScreenState.Loading)
    val successState = mutableStateOf<SuccessState>(SuccessState.NotEmptyChat)
    val listMessage = mutableStateOf(emptyList<Message>())

    fun loadData(id: String) = intent {
        postSideEffect(ChatSideEffects.ShowLoading)
        delay(2000)
        listMessage.value = listOf(
            Message(text = "some text from bot", from = MessageSender.Bot),
            Message(text = "some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot", from = MessageSender.Bot),
            Message(text = "some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from usersome text from usersome text from user", from = MessageSender.User),
            Message(text = "text", from = MessageSender.Bot),
            Message(text = "text from user", from = MessageSender.User),
            Message(text = "some text from bot", from = MessageSender.Bot),
            Message(text = "some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot", from = MessageSender.Bot),
            Message(text = "some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from usersome text from usersome text from user", from = MessageSender.User),
            Message(text = "text", from = MessageSender.Bot),
            Message(text = "text from user", from = MessageSender.User),
            Message(text = "some text from bot", from = MessageSender.Bot),
            Message(text = "some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot", from = MessageSender.Bot),
            Message(text = "some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from usersome text from usersome text from user", from = MessageSender.User),
            Message(text = "text", from = MessageSender.Bot),
            Message(text = "text from user", from = MessageSender.User),
        )
        postSideEffect(ChatSideEffects.ShowSuccess)
    }

    fun updateMessage(message: String) = intent {
        reduce {
            state.value = state.value.copy(chatState = state.value.chatState.copy(message = message))
            state
        }
    }

    fun sendMessage(text: String) = intent {
        listMessage.value = listMessage.value.plus(
            Message(text = text, from = MessageSender.User)
        )
        for (item in listMessage.value ) {
            Log.d("test", "sendMessage: ${item.text}")
        }
    }

}