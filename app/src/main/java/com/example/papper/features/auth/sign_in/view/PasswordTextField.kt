package com.example.papper.features.auth.sign_in.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.papper.R
import com.example.papper.features.auth.sign_in.presentation.SignInScreenState
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import com.example.papper.features.common.components.OutlinedPasswordTextFieldComponent
import com.example.papper.theme.Description
import com.example.papper.theme.dimens
import com.example.papper.utils.CheckAuthFields.checkPassword
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
) {
    val password = viewModel.collectAsState().value.password
    var textFieldState by remember {
        mutableStateOf(password)
    }

    Column {
        OutlinedPasswordTextFieldComponent(
            value = textFieldState,
            onValueChange = {
                viewModel.typingPassword(it)
                textFieldState = it
            },
            placeholder = stringResource(id = R.string.password),
            singleLine = true,
            keyboardType = KeyboardType.Password,
            isEnable = viewModel.signInScreenState.value !is SignInScreenState.Loading,
            isError = viewModel.signInScreenState.value is SignInScreenState.Error,
        )
        val msgAboutPassword = checkPassword(password = viewModel.collectAsState().value.password)
        if (msgAboutPassword != null) {
            Text(
                modifier = modifier
                    .padding(
                        start = MaterialTheme.dimens.gapBetweenComponentScreen,
                        end = MaterialTheme.dimens.gapBetweenComponentScreen,
                        top = MaterialTheme.dimens.bottomGap,
                        bottom = MaterialTheme.dimens.bottomGap,
                    ),
                text = msgAboutPassword,
                style = MaterialTheme.typography.Description,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }

}