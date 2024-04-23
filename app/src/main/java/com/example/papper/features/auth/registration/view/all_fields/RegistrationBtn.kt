package com.example.papper.features.auth.registration.view.all_fields

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.AllFieldsScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.ButtonComponent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun RegistrationBtn(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    val state = viewModel.collectAsState()
    ButtonComponent(
        modifier = modifier,
        onClick = {
            viewModel.createAccount()
        },
        text = stringResource(id = R.string.registration),
        isEnable = (
                state.value.name.isNotEmpty() && state.value.surname.isNotEmpty() &&
                state.value.login.isNotEmpty() && state.value.password.isNotEmpty() &&
                state.value.code.isNotEmpty() && (viewModel.allFieldScreenState.value !is AllFieldsScreenState.Loading)
        ),
        isLoading = viewModel.allFieldScreenState.value is AllFieldsScreenState.Loading,
    )
}