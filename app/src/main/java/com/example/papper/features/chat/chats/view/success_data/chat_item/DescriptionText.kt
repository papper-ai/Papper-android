package com.example.papper.features.chat.chats.view.success_data.chat_item

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.papper.theme.Description

@Composable
fun DescriptionText(
    modifier: Modifier = Modifier,
    description: String,
) {
    Text(
        modifier = modifier,
        text = description,
        style = MaterialTheme.typography.Description,
        color = MaterialTheme.colorScheme.onPrimary,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}