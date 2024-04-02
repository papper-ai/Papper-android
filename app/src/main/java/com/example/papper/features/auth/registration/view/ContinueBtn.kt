package com.example.papper.features.auth.registration.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.ButtonComponent1

@Composable
fun ContinueBtn(
    onClick: () -> Unit,
    viewModel: RegistrationViewModel,
    isEnable: Boolean = true,

) {
    ButtonComponent1(
        onClick = { onClick() },
        text = stringResource(id = R.string.next),
        isEnable = isEnable
    )
}