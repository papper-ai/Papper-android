package com.example.papper.features.chat.chat.view.success_data.not_empty_chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun NotEmptyBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        MessagesColumn(
            list = viewModel.collectAsState().value.listOfMessages,
        )
    }
}