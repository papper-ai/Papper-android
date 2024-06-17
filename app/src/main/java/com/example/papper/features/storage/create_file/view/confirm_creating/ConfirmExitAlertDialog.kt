package com.example.papper.features.storage.create_file.view.confirm_creating

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.AlertDialogComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileScreenState
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel

@Composable
fun ConfirmExitAlertDialog(
    modifier: Modifier = Modifier,
    onDismiss : () -> Unit,
    viewModel: CreateFileViewModel,
    showDialog: Boolean,
) {
    AlertDialogComponent(
        onConfirm = {
            viewModel.clearConvertedText()
            viewModel.createFileScreenState.value = CreateFileScreenState.AttachPhotos
        },
        onDismiss = { onDismiss() },
        showDialog = showDialog,
        title = stringResource(id = R.string.confirm_exit),
        text = stringResource(id = R.string.confirm_exit_text),
        confirmText = stringResource(id = R.string.confirm),
        cancelText = stringResource(id = R.string.cancel),
    )
}
