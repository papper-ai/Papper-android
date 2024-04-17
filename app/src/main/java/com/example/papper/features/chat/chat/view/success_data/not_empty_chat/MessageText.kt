package com.example.papper.features.chat.chat.view.success_data.not_empty_chat

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.theme.Buttons

@Composable
fun MessageText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.Buttons,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}