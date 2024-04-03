package com.example.papper.features.chat.chats

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.chats.presentation.ChatsScreenState
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.chat.chats.view.error_data.ErrorBasic
import com.example.papper.features.chat.chats.view.loading_data.LoadingBasic
import com.example.papper.features.chat.chats.view.success_data.SuccessBasic
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ChatBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
    navHostController: NavHostController,
) {
    when (viewModel.chatsScreenState.value) {
        is ChatsScreenState.Loading -> LoadingBasic(
            modifier = modifier,
            navHostController = navHostController,
        )
        is ChatsScreenState.Success -> SuccessBasic(
            modifier = modifier,
            navHostController = navHostController,
            list = viewModel.collectAsState().value.value.chatsState.listOfChats
        )
        is ChatsScreenState.Error -> ErrorBasic(
            modifier = modifier,
            viewModel = viewModel,
            navHostController = navHostController,
        )
    }
}