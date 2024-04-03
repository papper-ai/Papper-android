package com.example.papper.features.chat.chats.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.papper.base.BaseViewModel
import com.example.papper.features.chat.chats.model.ChatDescription
import com.example.papper.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
) : BaseViewModel<ChatsSideEffects>(appState) {

    val chatsScreenState = mutableStateOf<ChatsScreenState>(ChatsScreenState.Loading)

    init {
        getData()
    }

    private fun getData() = intent {
        val list = mutableListOf<ChatDescription>()
        postSideEffect(ChatsSideEffects.ShowLoading)
        viewModelScope.launch {
            for (i in 1..15) {
                delay(100)
                list.add(
                    ChatDescription(
                        id = i.toString(),
                        title = "title",
                        lastMsg = "last msg"
                    )
                )
                Log.d("Test", "getData: ${i}")
            }
            reduce {
                state.value = state.value.copy(chatsState = state.value.chatsState.copy(listOfChats = list))
                state
            }
            postSideEffect(ChatsSideEffects.ShowError)
        }
    }

}