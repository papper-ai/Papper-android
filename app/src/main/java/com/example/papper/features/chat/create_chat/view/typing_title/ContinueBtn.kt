package com.example.papper.features.chat.create_chat.view.typing_title

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.features.common.components.ButtonComponent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ContinueBtn(
    onClick: () -> Unit,
    viewModel: CreateChatViewModel,
) {
    ButtonComponent(
        onClick = { onClick() },
        text = stringResource(id = R.string.next),
        isEnable = viewModel.collectAsState().value.title.isNotEmpty()
    )
}