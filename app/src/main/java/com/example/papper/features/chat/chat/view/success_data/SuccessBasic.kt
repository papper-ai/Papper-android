package com.example.papper.features.chat.chat.view.success_data

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.chat.chat.presentation.SuccessState
import com.example.papper.features.chat.chat.view.success_data.bottom_bar.BottomBar
import com.example.papper.features.chat.chat.view.success_data.empty_chat.EmptyBasic
import com.example.papper.features.chat.chat.view.success_data.not_empty_chat.NotEmptyBasic
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    navHostController: NavHostController,
) {
    Scaffold(
        topBar = {
            TopBar(
                viewModel = viewModel,
                navHostController = navHostController,
                title = viewModel.collectAsState().value.title,
                archiveStatus = viewModel.collectAsState().value.isArchived
            )
        },

        bottomBar = {
            BottomBar(
                viewModel = viewModel
            )
        },

        modifier = modifier,
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background,
            ) {
                when (viewModel.successState.value) {
                    SuccessState.EmptyChat -> {
                        EmptyBasic()
                    }
                    SuccessState.NotEmptyChat -> {
                        NotEmptyBasic(
                            viewModel = viewModel,
                        )
                    }
                }
            }
        }
    )


}