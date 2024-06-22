package com.example.papper.features.chat.chats.view.error_data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.theme.dimens

@Composable
fun ErrorBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ErrorMsg()
        Box(
            modifier = Modifier
                .padding(bottom = MaterialTheme.dimens.bottomGap3),
            contentAlignment = Alignment.BottomCenter
        ) {
            ReloadBtn(viewModel = viewModel)
        }
    }

}