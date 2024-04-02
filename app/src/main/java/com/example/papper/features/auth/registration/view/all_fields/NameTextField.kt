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
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.OutlinedTextFieldComponent
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NameTextField(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    val name = viewModel.collectAsState().value.value.registrationState.name
    var state by remember {
        mutableStateOf(name)
    }

    OutlinedTextFieldComponent(
        value = state,
        modifier = modifier,
        onValueChange = { newName ->
            viewModel.updateName(newName)
            state = newName
        },
        placeholder = stringResource(id = R.string.name),
        singleLine = true
    )
}