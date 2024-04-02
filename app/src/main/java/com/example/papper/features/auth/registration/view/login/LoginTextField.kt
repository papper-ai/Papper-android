package com.example.papper.features.auth.registration.view.login

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.WithoutlinedTextFieldComponent
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    val login = viewModel.collectAsState().value.value.registrationState.login
    var textFieldState by remember {
        mutableStateOf(login)
    }

    WithoutlinedTextFieldComponent(
        value = textFieldState,
        onValueChange = { newName ->
            viewModel.updateLogin(newName)
            textFieldState = newName
        },
        modifier = modifier,
        placeholder = stringResource(id = R.string.fill_login),
        singleLine = true
    )
}