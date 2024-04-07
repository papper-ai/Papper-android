package com.example.papper.features.auth.registration.view.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.ButtonComponent1

@Composable
fun ReloadBtn(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel
) {
    ButtonComponent1(
        modifier = modifier,
        onClick = { /*TODO повторный запрос на создание аккаунта*/ },
        text = stringResource(id = R.string.retry_response)
    )
}