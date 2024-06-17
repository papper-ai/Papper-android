package com.example.papper.features.storage.create_file.view.confirm_creating

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.AlertDialogComponent

@Composable
fun ConfirmDeleteAlertDialog(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit,
    onDismiss : () -> Unit,
    text: String,
    showDialog: Boolean,
) {
    AlertDialogComponent(
        onConfirm = { onConfirm() },
        onDismiss = { onDismiss() },
        showDialog = showDialog,
        title = stringResource(id = R.string.delete_photo),
        text = text,
        confirmText = stringResource(id = R.string.delete),
        cancelText = stringResource(id = R.string.cancel),
    )
}