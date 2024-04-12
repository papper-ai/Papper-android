package com.example.papper.features.archive.view.success_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.chats.model.ChatDescription

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    list: List<ChatDescription>
) {
    ColumnOfChats(
        modifier = modifier,
        navHostController = navHostController,
        listOfChats = list
    )
}