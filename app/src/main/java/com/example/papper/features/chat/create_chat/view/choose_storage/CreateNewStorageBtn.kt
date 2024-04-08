package com.example.papper.features.chat.create_chat.view.choose_storage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.SmallButtonComponent

@Composable
fun CreateNewStorageBtn(
    modifier: Modifier = Modifier,
) {
    SmallButtonComponent(
        modifier = modifier,
        onClick = {

        },
        text = stringResource(id = R.string.create_new),
    )
}
