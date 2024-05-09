package com.example.papper.features.auth.registration.view.code

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
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.WithoutlinedTextFieldComponent
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CodeTextField(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    val code = viewModel.collectAsState().value.code
    var textFieldState by remember {
        mutableStateOf(code)
    }

    WithoutlinedTextFieldComponent(
        value = textFieldState,
        onValueChange = { newName ->
            viewModel.updateCode(newName)
            textFieldState = newName
        },
        modifier = modifier,
        placeholder = stringResource(id = R.string.fill_code),
        singleLine = true,
        keyboardType = KeyboardType.Text,
        keyboardCapitalization = KeyboardCapitalization.None,
    )
}