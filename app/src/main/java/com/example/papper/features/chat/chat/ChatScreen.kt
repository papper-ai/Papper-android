package com.example.papper.features.chat.chat

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.chat.chat.presentation.ChatScreenState
import com.example.papper.features.chat.chat.presentation.ChatSideEffects
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.navigation.Screens
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    navHostController: NavHostController,
    id: String,
    file: FilePresentationModel?,
) {
    val context = LocalContext.current
    viewModel.id = id
    if (file != null) {

    }

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            viewModel = viewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
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
    navHostController: NavHostController,
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

        is ChatSideEffects.NavigateToStorageScreen -> {
            navHostController.navigate("${Screens.StorageScreen.route}/${sideEffect.storageId}")
        }
    }
}