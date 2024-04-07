package com.example.papper.features.auth.sign_in.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.auth.sign_in.presentation.SignInScreenState
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import com.example.papper.features.common.components.ButtonComponent1

@Composable
fun SignInBtn(
    viewModel: SignInViewModel,
    navHostController: NavHostController
) {
    ButtonComponent1(
        onClick = {
            viewModel.signInClick()

        },
        isEnable = viewModel.fieldsStatus.value,
        text = stringResource(id = R.string.sign_in2),
        isLoading = (viewModel.signInScreenState.value is SignInScreenState.Loading)
    )
}