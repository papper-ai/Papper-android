package com.example.papper.features.chat.chat.view.success_data.bottom_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.theme.dimens

@Composable
fun AttachFileBtn(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable {
                viewModel.navigateToStorageScreenForResult()
            }
            .padding(end = MaterialTheme.dimens.gapBetweenComponentScreen),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = modifier,
            painter = painterResource(id = R.drawable.attach_file_icon),
            contentDescription = "Profile",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}