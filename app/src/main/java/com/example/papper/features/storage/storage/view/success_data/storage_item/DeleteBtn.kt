package com.example.papper.features.storage.storage.view.success_data.storage_item

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
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.features.storage.storage.presentation.StorageViewModel

@Composable
fun DeleteBtn(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    file: FilePresentationModel
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
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