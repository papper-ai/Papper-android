package com.example.papper.features.chat.chats.view.success_data.chat_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.features.chat.chats.model.ChatDescription
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.theme.dimens

@Composable
fun ItemBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
    chat: ChatDescription,
) {
    Row(
        modifier = modifier
            .clickable {
                viewModel.navigateToChatScreen(id = chat.id)
            }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        BotIcon()
        Column(
            modifier = modifier
                .padding(start = MaterialTheme.dimens.gapBetweenComponents),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            TitleText(title = chat.title)
            //DescriptionText(description = chat.lastMsg)
        }
    }
}