package com.example.papper.features.chat.chats.view.success_data

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.chat.chats.model.ChatDescription
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.chat.chats.view.success_data.top_bar.TopBarBasic

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
    navHostController: NavHostController,
    list: List<ChatDescription>
) {
    Scaffold(
        topBar = {
            TopBarBasic(
                navHostController = navHostController,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.navigateToCreateChatScreen()
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                content = {
                    Icon(painter = painterResource(id = R.drawable.message_icon) , contentDescription = "add_chat")
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background,
            ) {
                ColumnOfChats(
                    modifier = modifier,
                    viewModel = viewModel,
                    listOfChats = list
                )
            }
        },
    )
}