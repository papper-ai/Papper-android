package com.example.papper.features.storage.create_storage.view.typing_title

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.ButtonComponent
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel

@Composable
fun ContinueBtn(
    viewModel: CreateStorageViewModel,
    isEnable: Boolean = true,
) {
    ButtonComponent(
        onClick = { viewModel.toAttachFiles() },
        text = stringResource(id = R.string.next),
        isEnable = isEnable
    )
}