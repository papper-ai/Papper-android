package com.example.papper.features.chat.chat.view.success_data.bottom_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.features.chat.chat.presentation.ChatViewModel
import com.example.papper.features.common.components.CardComponent
import com.example.papper.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
) {
    CardComponent {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.dimens.cardCornerRadius,
                    bottom = MaterialTheme.dimens.cardCornerRadius,
                )
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            MessageTextField(
                modifier = Modifier.weight(1f),
                viewModel = viewModel,
                message = viewModel.collectAsState().value.message
            )
            //TODO раскоментить, когда на бэке появится этот функционал
            //AttachFileBtn(viewModel = viewModel)
        }
    }
}