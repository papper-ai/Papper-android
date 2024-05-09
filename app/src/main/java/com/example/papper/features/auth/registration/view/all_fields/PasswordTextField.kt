package com.example.papper.features.auth.registration.view.all_fields

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.AllFieldsScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.OutlinedPasswordTextFieldComponent
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    val password = viewModel.collectAsState().value.password
    var state by remember {
        mutableStateOf(password)
    }

    OutlinedPasswordTextFieldComponent(
        modifier = modifier,
        onValueChange = { newPassword ->
            viewModel.updatePassword(newPassword)
            state = newPassword
        },
        value = state,
        placeholder = stringResource(id = R.string.password),
        singleLine = true,
        keyboardType = KeyboardType.Password,
        keyboardCapitalization = KeyboardCapitalization.None,
        isEnable = viewModel.allFieldScreenState.value !is AllFieldsScreenState.Loading,
    )
}