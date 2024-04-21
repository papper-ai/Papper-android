package com.example.papper.features.chat.chat

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.papper.R
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
    val context = LocalContext.current
    viewModel.id = id

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            viewModel = viewModel,
            sideEffect = sideEffect,
            context = context,
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
    context: Context,
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

        ChatSideEffects.ShowErrorToast -> {
            Toast.makeText(context, context.getText(R.string.send_message_error), Toast.LENGTH_SHORT).show()
        }
    }
}