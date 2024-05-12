package com.example.papper.features.archive.view.success_data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.archive.view.success_data.archive_item.ItemBasic
import com.example.papper.features.chat.chats.model.ChatDescription
import com.example.papper.theme.dimens

@Composable
fun ColumnOfChats(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    listOfChats: List<ChatDescription>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(bottom = MaterialTheme.dimens.bottomGap)
    ) {
        items(
            items = listOfChats,
        ) { chat ->
            ItemBasic(navHostController = navHostController, chat = chat)
            Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
        }
    }
}