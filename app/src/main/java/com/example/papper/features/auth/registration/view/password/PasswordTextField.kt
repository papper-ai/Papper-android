package com.example.papper.features.auth.registration.view.password

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
fun PasswordTextField(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    val password = viewModel.collectAsState().value.value.registrationState.password
    var textFieldState by remember {
        mutableStateOf(password)
    }

    WithoutlinedTextFieldComponent(
        value = textFieldState,
        onValueChange = { newName ->
            viewModel.updatePassword(newName)
            textFieldState = newName
        },
        modifier = modifier,
        placeholder = stringResource(id = R.string.fill_password),
        singleLine = true
    )
}
