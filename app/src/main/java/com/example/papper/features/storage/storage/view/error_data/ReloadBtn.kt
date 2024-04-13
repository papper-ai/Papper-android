package com.example.papper.features.storage.storage.view.error_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.ButtonComponent
import com.example.papper.features.storage.storage.presentation.StorageViewModel

@Composable
fun ReloadBtn(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel
) {
    ButtonComponent(
        modifier = modifier,
        onClick = { /*TODO повторный запрос на получение чатов*/ },
        text = stringResource(id = R.string.retry_response)
    )
}