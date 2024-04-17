package com.example.papper.features.chat.chat.view.success_data.empty_chat

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.papper.R

@Composable
fun ChatIcon(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.big_message_icon),
        contentDescription = "Message",
        tint = MaterialTheme.colorScheme.onPrimary,
    )
}