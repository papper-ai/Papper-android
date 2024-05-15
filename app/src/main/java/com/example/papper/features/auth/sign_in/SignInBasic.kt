package com.example.papper.features.auth.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import com.example.papper.features.auth.sign_in.view.LoginTextField
import com.example.papper.features.auth.sign_in.view.PasswordTextField
import com.example.papper.features.auth.sign_in.view.SignInBtn
import com.example.papper.features.auth.sign_in.view.SignInText
import com.example.papper.features.common.components.BigLogoComponent
import com.example.papper.features.common.components.CardComponent
import com.example.papper.theme.dimens

@Composable
fun SignInBasic(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BigLogoComponent(
                modifier.weight(1f),
            )
            CardComponent {
                SignInText()
                LoginTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
                PasswordTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
                SignInBtn(viewModel = viewModel)
//                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
//                ForgotPasswordBtn()
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
            }
        }
    }
}