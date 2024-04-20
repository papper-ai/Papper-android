package com.example.papper.features.chat.chat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.chat.presentation.ChatScreenState
import com.example.papper.features.chat.chat.presentation.ChatSideEffects
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    navHostController: NavHostController,
    id: String,
) {
    viewModel.id = id

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            viewModel = viewModel,
            sideEffect = sideEffect,
        )
    }

    ChatBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController,
    )
}

private fun handleSideEffects(
    viewModel: ChatViewModel,
    sideEffect: ChatSideEffects,
) {
    when (sideEffect) {
        ChatSideEffects.ShowLoading -> {
            viewModel.chatScreenState.value = ChatScreenState.Loading
        }
        ChatSideEffects.ShowSuccess -> {
            viewModel.chatScreenState.value = ChatScreenState.Success
        }
        ChatSideEffects.ShowError -> {
            viewModel.chatScreenState.value = ChatScreenState.Error
        }
    }
}