package com.example.papper.features.auth.sign_in.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.papper.R
import com.example.papper.features.auth.sign_in.presentation.SignInScreenState
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import com.example.papper.features.common.components.OutlinedTextFieldComponent
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PasswordTextField(
    viewModel: SignInViewModel,
    //navHostController: NavHostController,
) {
    val password = viewModel.collectAsState().value.password
    var textFieldState by remember {
        mutableStateOf(password)
    }
    OutlinedTextFieldComponent(
        value = textFieldState,
        onValueChange = {
            viewModel.typingPassword(it)
            textFieldState = it
        },
        placeholder = stringResource(id = R.string.password),
        singleLine = true,
        keyboardType = KeyboardType.Password,
        isEnable = viewModel.signInScreenState.value !is SignInScreenState.Loading,
    )
}