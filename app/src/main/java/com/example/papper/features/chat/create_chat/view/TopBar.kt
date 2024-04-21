package com.example.papper.features.chat.create_chat.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.create_chat.presentation.CreateChatScreenState
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.features.common.components.TopBarWithLogoComponent

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: CreateChatViewModel,
    navHostController: NavHostController,
) {
    TopBarWithLogoComponent(
        modifier = modifier,
        onClick = {
            when (viewModel.createChatScreenState.value) {
                CreateChatScreenState.TypingTitle -> {
                    navHostController.popBackStack()
                }
                CreateChatScreenState.ChooseStorage -> {
                    viewModel.createChatScreenState.value = CreateChatScreenState.TypingTitle
                }
            }
        }
    )
}