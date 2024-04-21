package com.example.papper.features.chat.create_chat

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.create_chat.presentation.ChooseStorageScreenState
import com.example.papper.features.chat.create_chat.presentation.CreateChatScreenState
import com.example.papper.features.chat.create_chat.presentation.CreateChatSideEffects
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.navigation.Screens
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CreateChatScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateChatViewModel,
    navHostController: NavHostController,
    id: String? = null
) {
    if (id != null) {
        viewModel.toListOfFiles(id = id)
    }
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
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
    navHostController: NavHostController,
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
    }
}