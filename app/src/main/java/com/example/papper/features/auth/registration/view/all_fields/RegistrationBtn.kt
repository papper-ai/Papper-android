package com.example.papper.features.auth.registration.view.all_fields

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.AllFieldsScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.ButtonComponent

@Composable
fun RegistrationBtn(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
    navHostController: NavHostController,
) {
    ButtonComponent(
        modifier = modifier,
        onClick = {
            viewModel.createAccount(navHostController = navHostController)
        },
        text = stringResource(id = R.string.registration),
        isEnable = (
                (viewModel.nameStatus.value && viewModel.surnameStatus.value &&
                viewModel.loginStatus.value && viewModel.passwordStatus.value &&
                viewModel.codeStatus.value) && (viewModel.allFieldScreenState.value !is AllFieldsScreenState.Loading)
        ),
        isLoading = viewModel.allFieldScreenState.value is AllFieldsScreenState.Loading,
    )
}