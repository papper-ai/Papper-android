package com.example.papper.features.auth.registration.view.all_fields

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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.papper.R
import com.example.papper.features.auth.registration.presentation.AllFieldsScreenState
import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
import com.example.papper.features.common.components.OutlinedPasswordTextFieldComponent
import com.example.papper.theme.Description
import com.example.papper.theme.dimens
import com.example.papper.utils.CheckAuthFields
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

    Column {
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
        val msgAboutPassword = CheckAuthFields.checkPassword(password = viewModel.collectAsState().value.password)
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