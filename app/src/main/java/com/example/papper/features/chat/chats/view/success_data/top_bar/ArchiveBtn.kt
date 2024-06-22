package com.example.papper.features.chat.chats.view.success_data.top_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.features.chat.chats.presentation.ChatsViewModel

@Composable
fun ArchiveBtn(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable {
                viewModel.navigateToArchivesScreen()
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.archive_icon),
            contentDescription = "Archive",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}