package com.example.papper.features.chat.chat.view.success_data.empty_chat

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.theme.Heading2

@Composable
fun StartChatText(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = R.string.start_chatting),
        style = MaterialTheme.typography.Heading2,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}