package com.example.papper.features.auth.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import com.example.papper.features.auth.sign_in.view.ForgotPasswordBtn
import com.example.papper.features.auth.sign_in.view.LoginTextField
import com.example.papper.features.auth.sign_in.view.PasswordTextField
import com.example.papper.features.auth.sign_in.view.SignInBtn
import com.example.papper.features.common.components.BigLogoComponent
import com.example.papper.theme.Heading1
import com.example.papper.theme.dimens

@Composable
fun SignInBasic(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
    navHostController: NavHostController,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            BigLogoComponent()
        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = modifier
                    .wrapContentHeight()
                    .fillMaxWidth(1f),
                shape = RoundedCornerShape(topStart = MaterialTheme.dimens.cardCornerRadius, topEnd = MaterialTheme.dimens.cardCornerRadius),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                )
            ) {
                Column(
                    modifier = modifier
                        .wrapContentHeight()
                        .fillMaxWidth(1f),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.dimens.gapBetweenComponentScreen),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Text(
                            text = stringResource(id = R.string.sign_in),
                            style = MaterialTheme.typography.Heading1,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    LoginTextField(viewModel = viewModel)
                    Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
                    PasswordTextField(viewModel = viewModel)
                    Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
                    SignInBtn(viewModel = viewModel, navHostController = navHostController)
                    Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
                    ForgotPasswordBtn(
                        onClick = {/*todo поработать с сервером, отправить запрос на востановление пароля*/}  ,
                    )
                    Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
                }
            }
        }
    }
}