package com.example.papper.features.chat.create_chat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.create_chat.presentation.CreateChatScreenState
import com.example.papper.features.chat.create_chat.presentation.CreateChatSideEffects
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CreateChatScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateChatViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect,
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
    sideEffect: CreateChatSideEffects,
) {
    when (sideEffect) {
        CreateChatSideEffects.ShowChooseStorageScreen -> {
            viewModel.createChatScreenState.value = CreateChatScreenState.ChooseStorage
        }
        CreateChatSideEffects.ShowListOfFilesScreen -> {
            viewModel.createChatScreenState.value = CreateChatScreenState.ListOfFiles
        }
    }
}