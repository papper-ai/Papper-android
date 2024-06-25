package com.example.papper.features.storage.storage.view.success_data.storage_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.features.common.components.AlertDialogComponent
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.features.storage.storage.presentation.StorageViewModel

@Composable
fun DeleteBtn(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    file: FilePresentationModel,
) {
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        AlertDialogComponent(
            showDialog = true,
            onConfirm = {viewModel.deleteFile(file)},
            onDismiss = {openDialog.value = false},
            title = stringResource(id = R.string.deleting_file_title),
            text = "${stringResource(id = R.string.deleting_file_description_part1)} ${file.title} ${stringResource(id = R.string.from_storage_part2)}",
            confirmText = stringResource(id = R.string.delete),
            cancelText = stringResource(id = R.string.cancel),
        )
    }

    Box(
        modifier = Modifier
            .padding(start = 5.dp)
            .size(30.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.error)
            .clickable {
                openDialog.value = true
            },
        contentAlignment = Alignment.Center,
    ) {
        var flag = false
        for (item in viewModel.fileDeleteLoading.value) {
            if (item.first && item.second == file.id) {
                flag = true
            }
        }

        if (flag) {
            CircularProgressIndicator(
                modifier = modifier
                    .size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 3.dp
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.error_icon_small),
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}