package com.example.papper.features.chat.chats.view.success_data.top_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.theme.dimens

@Composable
fun TopBarBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen - 15.dp,
                bottom = MaterialTheme.dimens.bottomGap2,
                top = MaterialTheme.dimens.bottomGap
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Logo()
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End
        ) {
            StorageBtn(
                viewModel = viewModel,
            )
            ArchiveBtn(
                viewModel = viewModel,
            )
            ProfileBtn(
                viewModel = viewModel,
            )
        }
    }
}