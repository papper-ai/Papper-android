package com.example.papper.features.auth.sign_in.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.sign_in.presentation.SignInScreenState
import com.example.papper.features.auth.sign_in.presentation.SignInViewModel
import com.example.papper.features.common.components.OutlinedTextFieldComponent
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
    //navHostController: NavHostController,
) {
    val login = viewModel.collectAsState().value.value.signInState.login
    var textFieldState by remember {
        mutableStateOf(login)
    }
    OutlinedTextFieldComponent(
        value = textFieldState,
        onValueChange = {
            viewModel.typingLogin(it)
            textFieldState = it
        },
        placeholder = stringResource(id = R.string.email),
        singleLine = true,
        isEnable = viewModel.signInScreenState.value !is SignInScreenState.Loading,
    )
}