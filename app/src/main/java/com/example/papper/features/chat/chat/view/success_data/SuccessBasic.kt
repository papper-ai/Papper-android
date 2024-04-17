package com.example.papper.features.chat.chat.view.success_data

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.chat.chat.model.MessageSender
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.chat.chat.presentation.SuccessState
import com.example.papper.features.chat.chat.view.success_data.bottom_bar.BottomBar
import com.example.papper.features.chat.chat.view.success_data.empty_chat.EmptyBasic
import com.example.papper.features.chat.chat.view.success_data.not_empty_chat.NotEmptyBasic
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    navHostController: NavHostController,
) {
    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController,
                title = viewModel.collectAsState().value.value.chatState.title
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