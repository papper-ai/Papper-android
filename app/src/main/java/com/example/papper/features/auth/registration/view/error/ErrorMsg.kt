package com.example.papper.features.auth.registration.view.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.ErrorMessageComponent

@Composable
fun ErrorMsg(
    modifier: Modifier = Modifier,
) {
    ErrorMessageComponent(
        errorText = stringResource(id = R.string.create_acc_error)
    )
}