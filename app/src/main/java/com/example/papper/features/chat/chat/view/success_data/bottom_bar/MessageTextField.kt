package com.example.papper.features.chat.chat.view.success_data.bottom_bar

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.common.components.ChatOutlinedTextFieldComponent
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
) {
    val message = viewModel.collectAsState().value.message
    var textFieldState by remember {
        mutableStateOf(message)
    }

    ChatOutlinedTextFieldComponent(
        modifier = modifier,
        onValueChange = { message ->
            viewModel.updateMessage(message)
            textFieldState = message
        },
        value = textFieldState,
        placeholder = stringResource(id = R.string.message),
        singleLine = false,
        onClick = {
            viewModel.sendMessage(text = textFieldState)
            textFieldState = ""
        },
        isBtnEnable = textFieldState.isNotEmpty()
    )
}