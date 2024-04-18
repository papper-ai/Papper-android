package com.example.papper.features.auth.registration.view.all_fields

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.AllFieldsScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.OutlinedTextFieldComponent
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    val login = viewModel.collectAsState().value.login
    var state by remember {
        mutableStateOf(login)
    }

    OutlinedTextFieldComponent(
        modifier = modifier,
        onValueChange = { newLogin ->
            viewModel.updateLogin(newLogin)
            state = newLogin
        },
        value = state,
        placeholder = stringResource(id = R.string.login),
        singleLine = true,
        isEnable = viewModel.allFieldScreenState.value !is AllFieldsScreenState.Loading,
    )
}