package com.example.papper.features.chat.chat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.chat.presentation.ChatScreenState
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.chat.chat.view.error_data.ErrorBasic
import com.example.papper.features.chat.chat.view.loading_data.LoadingBasic
import com.example.papper.features.chat.chat.view.success_data.SuccessBasic

@Composable
fun ChatBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    navHostController: NavHostController,
) {
    when (viewModel.chatScreenState.value) {
        ChatScreenState.Loading -> {
            LoadingBasic(
                navHostController = navHostController,
            )
        }
        ChatScreenState.Success -> {
            SuccessBasic(
                viewModel = viewModel,
                navHostController = navHostController,
            )
        }
        ChatScreenState.Error -> {
            ErrorBasic(
                viewModel = viewModel,
                navHostController = navHostController,
            )
        }
    }
}