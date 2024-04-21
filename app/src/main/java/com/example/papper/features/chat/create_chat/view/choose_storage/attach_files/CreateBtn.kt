package com.example.papper.features.chat.create_chat.view.choose_storage.attach_files

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.features.common.components.ButtonComponent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CreateBtn(
    viewModel: CreateChatViewModel,
    isEnable: Boolean,
) {
    ButtonComponent(
        onClick = {

        },
        text = stringResource(id = R.string.create),
        isEnable = isEnable,
    )
}