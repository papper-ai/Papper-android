package com.example.papper.features.chat.create_chat

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.chat.create_chat.presentation.ChooseStorageScreenState
import com.example.papper.features.chat.create_chat.presentation.CreateChatScreenState
import com.example.papper.features.chat.create_chat.presentation.CreateChatSideEffects
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectSideEffect

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CreateChatScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateChatViewModel,
    chatsViewModel: ChatsViewModel,
    navHostController: NavHostController,
    id: String? = null
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    coroutineScope.launch {
        delay(10)
        if (id != null) {
            viewModel.toListOfFiles(id = id)
        }
    }

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            chatsViewModel = chatsViewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
            context = context,
        )
    }
    CreateChatBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController
    )
}

private fun handleSideEffect(
    viewModel: CreateChatViewModel,
    chatsViewModel: ChatsViewModel,
    sideEffect: CreateChatSideEffects,
    navHostController: NavHostController,
    context: Context,
) {
    when (sideEffect) {
        is CreateChatSideEffects.ShowChooseStorageScreen -> {
            viewModel.createChatScreenState.value = CreateChatScreenState.ChooseStorage
        }
        is CreateChatSideEffects.ShowListOfFilesScreen -> {
            viewModel.chooseStorageScreenState.value = ChooseStorageScreenState.ListOfFiles
        }
        is CreateChatSideEffects.ShowLoading -> {
            viewModel.chooseStorageScreenState.value = ChooseStorageScreenState.Loading
        }
        is CreateChatSideEffects.NavigateToStoragesScreen -> {
            navHostController.navigate(Screens.StoragesScreen.route)
        }
        is CreateChatSideEffects.NavigateToChatScreen -> {
            chatsViewModel.loadData()
            navHostController.navigate("${Screens.ChatScreen.route}/${sideEffect.id}") {
                popUpTo(Screens.CreateChatScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true

            }
        }
        CreateChatSideEffects.ShowFetchStorageError -> {
            Toast.makeText(context, context.getText(R.string.loading_storage_error), Toast.LENGTH_SHORT).show()
        }
        CreateChatSideEffects.ShowCreateChatErrorToast -> {
            Toast.makeText(context, context.getText(R.string.create_chat_error), Toast.LENGTH_SHORT).show()
        }
        CreateChatSideEffects.ShowNetworkConnectionError -> {
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
    }
}