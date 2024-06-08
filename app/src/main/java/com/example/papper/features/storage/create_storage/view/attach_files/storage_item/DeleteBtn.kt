package com.example.papper.features.storage.create_storage.view.attach_files.storage_item

import androidx.compose.foundation.background
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
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import java.io.File

@Composable
fun DeleteBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    file: File,
) {
    Box(
        modifier = Modifier
            .padding(start = 5.dp)
            .size(30.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.error)
            .clickable {
                viewModel.deleteFile(file)
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