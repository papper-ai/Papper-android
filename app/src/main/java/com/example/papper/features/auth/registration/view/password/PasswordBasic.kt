package com.example.papper.features.auth.registration.view.password

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.features.auth.registration.presentation.RegistrationScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.auth.registration.view.ContinueBtn
import com.example.papper.features.common.components.PageProgressComponent
import com.example.papper.theme.dimens

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PasswordBasic(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    BackHandler {
        viewModel.registrationScreenState.value = RegistrationScreenState.TypingLogin
    }

    Box(
        modifier = modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.gapBetweenComponents3))
            FillPasswordText()
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.gapBetweenComponents2))
            PasswordTextField(viewModel = viewModel)
            PasswordVisibilityBtn(viewModel = viewModel)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = modifier
        ) {
            PageProgressComponent(pageCount = 3, currentPage = 2)
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap2))
            ContinueBtn(
                onClick = {viewModel.toCode()},
                viewModel = viewModel,
                isEnable = viewModel.validatePassword.value,
            )
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap3))
        }
    }
}