package com.example.papper.features.chat.create_chat

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.create_chat.presentation.CreateChatScreenState
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.features.chat.create_chat.view.TopBar
import com.example.papper.features.chat.create_chat.view.choose_storage.ChooseStorageBasic
import com.example.papper.features.chat.create_chat.view.typing_title.TitleBasic

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CreateChatBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateChatViewModel,
    navHostController: NavHostController,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        when (viewModel.createChatScreenState.value) {
            CreateChatScreenState.TypingTitle -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        TitleBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                        )
                    }
                )
            }

            CreateChatScreenState.ChooseStorage -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        ChooseStorageBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    }
                )
            }
        }
    }
}