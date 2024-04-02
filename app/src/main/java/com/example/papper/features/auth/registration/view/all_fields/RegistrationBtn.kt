package com.example.papper.features.auth.registration.view.all_fields

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.ButtonComponent1
import com.example.papper.navigation.Screens

@Composable
fun RegistrationBtn(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
    navHostController: NavHostController,
) {
    ButtonComponent1(
        modifier = modifier,
        onClick = {
            navHostController.navigate(Screens.ChatsScreen.route)
        },
        text = stringResource(id = R.string.registration),
    )
}