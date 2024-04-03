package com.example.papper.features.chat.chats

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.chats.presentation.ChatsScreenState
import com.example.papper.features.chat.chats.presentation.ChatsSideEffects
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect
        )
    }
    ChatBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController
    )
}

private fun handleSideEffect(
    viewModel: ChatsViewModel,
    sideEffect: ChatsSideEffects
) {
    when (sideEffect) {
        ChatsSideEffects.ShowError -> {
            viewModel.chatsScreenState.value = ChatsScreenState.Error
        }
        ChatsSideEffects.ShowLoading -> {
            viewModel.chatsScreenState.value = ChatsScreenState.Loading
        }
        ChatsSideEffects.ShowSuccess -> {
            viewModel.chatsScreenState.value = ChatsScreenState.Success
        }
    }
}


