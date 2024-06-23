package com.example.papper.features.storage.create_file.view.confirm_creating

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.AlertDialogComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import kotlinx.coroutines.launch

@Composable
fun ConfirmExitAlertDialog(
    modifier: Modifier = Modifier,
    onDismiss : () -> Unit,
    showDialog: Boolean,
    viewModel: CreateFileViewModel,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    AlertDialogComponent(
        onConfirm = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(1)
            }
            viewModel.clearConvertedText()
        },
        onDismiss = { onDismiss() },
        showDialog = showDialog,
        title = stringResource(id = R.string.confirm_exit),
        text = stringResource(id = R.string.confirm_exit_text),
        confirmText = stringResource(id = R.string.confirm),
        cancelText = stringResource(id = R.string.cancel),
    )
}
