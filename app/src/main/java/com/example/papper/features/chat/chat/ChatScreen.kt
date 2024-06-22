package com.example.papper.features.chat.chat

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.chat.chat.presentation.ChatScreenState
import com.example.papper.features.chat.chat.presentation.ChatSideEffects
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.navigation.ArchivesScreen
import com.example.papper.navigation.ChatsScreen
import com.example.papper.navigation.StorageScreen
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    chatsViewModel: ChatsViewModel,
    archivesViewModel: ArchivesViewModel,
    navHostController: NavHostController,
    id: String,
) {
    val context = LocalContext.current
    viewModel.id = id

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            viewModel = viewModel,
            chatsViewModel = chatsViewModel,
            archivesViewModel = archivesViewModel,
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
    chatsViewModel: ChatsViewModel,
    archivesViewModel: ArchivesViewModel,
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
        ChatSideEffects.ShowErrorSendMsgToast -> {
            Toast.makeText(context, context.getText(R.string.send_message_error), Toast.LENGTH_SHORT).show()
        }
        ChatSideEffects.ShowErrorRenameToast -> {
            Toast.makeText(context, context.getText(R.string.rename_title_error), Toast.LENGTH_SHORT).show()
        }
        ChatSideEffects.ShowErrorDeleteChatToast -> {
            Toast.makeText(context, context.getText(R.string.delete_chat_error), Toast.LENGTH_SHORT).show()
        }
        is ChatSideEffects.RenameChat -> {
            when (navHostController.previousBackStackEntry?.destination?.route) {
                ChatsScreen.toString() -> chatsViewModel.loadData()
                ArchivesScreen.toString() -> archivesViewModel.loadData()
            }
        }
        is ChatSideEffects.NavigateToStorageScreen -> {
            navHostController.navigate(StorageScreen(storageId = sideEffect.storageId))
        }
        is ChatSideEffects.DeleteChatAndNavigateToChatsScreen -> {
            when (navHostController.previousBackStackEntry?.destination?.route) {
                ChatsScreen.toString() -> chatsViewModel.loadData()
                ArchivesScreen.toString() -> archivesViewModel.loadData()
            }
            navHostController.popBackStack()
        }
        is ChatSideEffects.ChangeArchiveStatus -> {
            chatsViewModel.loadData()
            archivesViewModel.loadData()
        }

        ChatSideEffects.ShowNetworkConnectionError -> {
            viewModel.chatScreenState.value = ChatScreenState.Error
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
    }
}