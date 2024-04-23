package com.example.papper.features.auth.sign_in.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.sign_in.presentation.SignInScreenState
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import com.example.papper.features.common.components.ButtonComponent

@Composable
fun SignInBtn(
    viewModel: SignInViewModel,
) {
    ButtonComponent(
        onClick = {
            viewModel.signIn()
        },
        isEnable = (viewModel.fieldsStatus.value && viewModel.signInScreenState.value !is SignInScreenState.Loading),
        text = stringResource(id = R.string.sign_in2),
        isLoading = (viewModel.signInScreenState.value is SignInScreenState.Loading)
    )
}