package com.example.papper.features.auth.start.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.start.presentation.StartViewModel
import com.example.papper.features.common.components.OutlinedButtonComponent

@Composable
fun SignUpBtn(
    modifier: Modifier = Modifier,
    viewModel: StartViewModel,
) {
    OutlinedButtonComponent(
        modifier = modifier,
        onClick = { viewModel.navigateToSignUpScreen() },
        text = stringResource(id = R.string.sign_up),
    )
}