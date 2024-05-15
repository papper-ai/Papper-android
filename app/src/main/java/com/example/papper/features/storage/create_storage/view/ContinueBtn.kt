package com.example.papper.features.storage.create_storage.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.ButtonComponent

@Composable
fun ContinueBtn(
    onClick: () -> Unit,
    isEnable: Boolean = true,
) {
    ButtonComponent(
        onClick = { onClick() },
        text = stringResource(id = R.string.next),
        isEnable = isEnable
    )
}