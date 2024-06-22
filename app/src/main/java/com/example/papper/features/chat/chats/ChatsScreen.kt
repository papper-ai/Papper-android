package com.example.papper.features.chat.chats

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.chat.chats.presentation.ChatsScreenState
import com.example.papper.features.chat.chats.presentation.ChatsSideEffects
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.navigation.ArchivesScreen
import com.example.papper.navigation.ChatScreen
import com.example.papper.navigation.CreateChatScreen
import com.example.papper.navigation.ProfileScreen
import com.example.papper.navigation.StoragesScreen
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
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

private fun handleSideEffect(
    viewModel: ChatsViewModel,
    sideEffect: ChatsSideEffects,
    navHostController: NavHostController,
    context: Context,
) {
    when (sideEffect) {
        ChatsSideEffects.ShowLoading -> {
            viewModel.chatsScreenState.value = ChatsScreenState.Loading
        }
        ChatsSideEffects.ShowSuccess -> {
            viewModel.chatsScreenState.value = ChatsScreenState.Success
        }
        ChatsSideEffects.ShowError -> {
            viewModel.chatsScreenState.value = ChatsScreenState.Error
        }
        ChatsSideEffects.ShowNetworkConnectionError -> {
            viewModel.chatsScreenState.value = ChatsScreenState.Error
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
        is ChatsSideEffects.NavigateToChatScreen -> {
            navHostController.navigate(ChatScreen(chatId = sideEffect.id))
        }
        ChatsSideEffects.NavigateToCreateChatScreen -> {
            navHostController.navigate(CreateChatScreen(storageId = null))
        }
        ChatsSideEffects.NavigateToArchivesScreen -> {
            navHostController.navigate(ArchivesScreen)
        }
        ChatsSideEffects.NavigateToProfileScreen -> {
            navHostController.navigate(ProfileScreen)
        }
        ChatsSideEffects.NavigateToStoragesScreen -> {
            navHostController.navigate(StoragesScreen)
        }
    }
}


