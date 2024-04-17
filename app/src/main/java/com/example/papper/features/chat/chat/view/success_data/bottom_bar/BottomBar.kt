package com.example.papper.features.chat.chat.view.success_data.bottom_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
) {
    CardComponent {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.cardCornerRadius))
            MessageTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.cardCornerRadius))
        }
    }
}