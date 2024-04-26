package com.example.papper.features.chat.chat.view.success_data.bottom_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.common.components.ChatOutlinedTextFieldComponent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
    message: String,
) {
    var textFieldState by remember {
        mutableStateOf(message)
    }

    ChatOutlinedTextFieldComponent(
        modifier = modifier,
        onValueChange = { newMessage ->
            viewModel.updateMessage(newMessage)
            textFieldState = newMessage
        },
        value = textFieldState,
        placeholder = stringResource(id = R.string.message),
        singleLine = false,
        onClick = {
            viewModel.sendMessage(text = textFieldState).invokeOnCompletion {
                if (viewModel.isSendingMessage.value.isSuccess) {
                    textFieldState = ""
                }
            }
        },
        isEnable = !viewModel.isSendingMessage.value.isSendingMsg,
        isBtnEnable = (textFieldState.isNotEmpty() && !viewModel.isSendingMessage.value.isSendingMsg),
        isSendingMsg = viewModel.isSendingMessage.value.isSendingMsg,
    )
}

data class MessageStatus(
    val isSendingMsg: Boolean,
    val isSuccess: Boolean,
)