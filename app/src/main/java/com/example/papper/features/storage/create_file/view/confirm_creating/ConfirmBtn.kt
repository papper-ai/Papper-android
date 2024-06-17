package com.example.papper.features.storage.create_file.view.confirm_creating

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.ButtonComponent

@Composable
fun ConfirmBtn(
    onClick: () -> Unit,
    isEnable: Boolean
) {
    ButtonComponent(
        onClick = { onClick() },
        text = stringResource(R.string.create),
        isEnable = (isEnable)
    )
}