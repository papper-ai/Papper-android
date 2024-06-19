package com.example.papper.features.chat.chats

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.chat.chats.presentation.ChatsScreenState
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.chat.chats.view.error_data.ErrorBasic
import com.example.papper.features.chat.chats.view.loading_data.LoadingBasic
import com.example.papper.features.chat.chats.view.success_data.SuccessBasic
import com.example.papper.features.chat.chats.view.success_data.top_bar.TopBarBasic
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ChatBasic(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
    navHostController: NavHostController,
) {
    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(500)
            viewModel.refreshData()
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = {
            refreshing = true
        },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopBarBasic(
                    navHostController = navHostController,
                )
            },
            floatingActionButton = {
                when (viewModel.chatsScreenState.value) {
                    is ChatsScreenState.Success -> {
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
                    }
                    else -> {}
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            content = {
                when (viewModel.chatsScreenState.value) {
                    is ChatsScreenState.Loading -> LoadingBasic(
                        modifier = modifier
                            .padding(it),
                    )
                    is ChatsScreenState.Success -> {
                        SuccessBasic(
                            modifier = modifier
                                .padding(it),
                            viewModel = viewModel,
                            list = viewModel.collectAsState().value.listOfChats
                        )
                    }
                    is ChatsScreenState.Error -> ErrorBasic(
                        modifier = modifier
                            .padding(it.calculateBottomPadding() - it.calculateBottomPadding()),
                        viewModel = viewModel,
                    )
                }
            },
        )
    }
}