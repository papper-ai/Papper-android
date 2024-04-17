package com.example.papper.features.chat.chat.view.success_data.empty_chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.papper.theme.dimens

@Composable
fun EmptyBasic(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChatIcon()
        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap))
        StartChatText()
    }
}

@Composable
@Preview
fun EmptyBasicPreview() {
    EmptyBasic()
}