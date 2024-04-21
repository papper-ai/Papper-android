package com.example.papper.features.chat.create_chat.view.choose_storage.choose_variable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.features.common.components.RadioBtnComponent


@Composable
fun SkipBtn(
    modifier: Modifier = Modifier,
    viewModel: CreateChatViewModel,
) {
    var radioBtnState by remember {
        mutableStateOf<Boolean>(false)
    }

    RadioBtnComponent(
        modifier = modifier,
        onClick = {
            radioBtnState = !radioBtnState
            viewModel.skipClicked(radioBtnState)
        },
        text = stringResource(id = R.string.skip),
        isSelected = radioBtnState,
    )
}