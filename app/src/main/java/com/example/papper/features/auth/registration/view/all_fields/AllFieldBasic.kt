package com.example.papper.features.auth.registration.view.all_fields

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.theme.dimens

@Composable
fun AllFieldBasic(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
    navHostController: NavHostController,
) {
    BackHandler {
        viewModel.registrationScreenState.value = RegistrationScreenState.TypingCode
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Logo(modifier = modifier.weight(1f))
        BackgroundCard {
            RegistrationText()
            NameTextField(viewModel = viewModel)
            Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
            SurnameTextField(viewModel = viewModel)
            Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
            LoginTextField(viewModel = viewModel)
            Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
            PasswordTextField(viewModel = viewModel)
            Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
            CodeTextField(viewModel = viewModel)
            Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
            RegistrationBtn(viewModel = viewModel, navHostController = navHostController)
            Spacer(modifier = modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
        }
    }
}