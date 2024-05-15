package com.example.papper.features.chat.chats.view.success_data

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.chat.chats.model.ChatDescription
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.chat.chats.view.success_data.chat_item.ItemBasic
import com.example.papper.theme.Heading2
import com.example.papper.theme.dimens

@Composable
fun ColumnOfChats(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
    listOfChats: List<ChatDescription>,
) {
    if (listOfChats.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        viewModel.navigateToCreateChatScreen()
                    },
                text = stringResource(id = R.string.nothing_there),
                style = MaterialTheme.typography.Heading2,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxHeight(),
            contentPadding = PaddingValues(bottom = MaterialTheme.dimens.bottomGap)
        ) {
            items(
                items = listOfChats,
            ) { chat ->
                ItemBasic(viewModel = viewModel, chat = chat)
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
            }
        }
    }
}