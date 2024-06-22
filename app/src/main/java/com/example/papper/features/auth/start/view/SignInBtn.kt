package com.example.papper.features.auth.start.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.start.presentation.StartViewModel
import com.example.papper.features.common.components.ButtonComponent

@Composable
fun SignInBtn(
    modifier: Modifier = Modifier,
    viewModel: StartViewModel,
) {
    ButtonComponent(
        modifier = modifier,
        onClick = {viewModel.navigateToSignInScreen()},
        text = stringResource(id = R.string.sign_in),
    )
}