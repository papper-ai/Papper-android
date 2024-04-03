package com.example.papper.features.chat.chats.view.error_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.common.components.ButtonComponent1

@Composable
fun ReloadBtn(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel
) {
    ButtonComponent1(
        modifier = modifier,
        onClick = { /*TODO повторный запрос на получение чатов*/ },
        text = stringResource(id = R.string.retry_response)
    )
}