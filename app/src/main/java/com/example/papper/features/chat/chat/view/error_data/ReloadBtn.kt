package com.example.papper.features.chat.chat.view.error_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.common.components.ButtonComponent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ReloadBtn(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel
) {
    ButtonComponent(
        modifier = modifier,
        onClick = { viewModel.loadData() },
        text = stringResource(id = R.string.retry_response)
    )
}