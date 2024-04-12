package com.example.papper.features.storage.create_storage.view.attach_files.storage_item

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
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel

@Composable
fun DeleteBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable {

            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.error_icon),
            contentDescription = "Delete",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}