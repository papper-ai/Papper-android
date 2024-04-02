package com.example.papper.features.auth.start.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.common.components.ButtonComponent2
import com.example.papper.navigation.Screens

@Composable
fun SignUpBtn(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    ButtonComponent2(
        modifier = modifier,
        onClick = { navHostController.navigate(Screens.RegistrationScreen.route)},
        text = stringResource(id = R.string.sign_up),
    )
}