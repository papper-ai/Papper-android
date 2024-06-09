package com.example.papper.features.chat.chats.view.success_data

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.chat.chats.model.ChatDescription
import com.example.papper.features.chat.chats.presentation.ChatsViewModel

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
    list: List<ChatDescription>
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        ColumnOfChats(
            viewModel = viewModel,
            listOfChats = list
        )
    }
}