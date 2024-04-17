package com.example.papper.features.chat.chat.view.success_data.not_empty_chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.state.AppState
import kotlinx.coroutines.flow.MutableStateFlow

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
            list = viewModel.listMessage.value,
        )
    }
}