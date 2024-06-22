package com.example.papper.features.chat.create_chat.view.choose_storage.choose_variable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.features.common.components.SmallButtonComponent

@Composable
fun CreateNewStorageBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateChatViewModel,
) {
    SmallButtonComponent(
        modifier = modifier,
        onClick = {
            viewModel.navigateToCreateStorageScreen()
        },
        text = stringResource(id = R.string.create_new),
    )
}
