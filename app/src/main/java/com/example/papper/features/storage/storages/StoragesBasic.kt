package com.example.papper.features.storage.storages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.presentation.StoragesScreenState
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.features.storage.storages.view.TopBar
import com.example.papper.features.storage.storages.view.error_data.ErrorBasic
import com.example.papper.features.storage.storages.view.loading_data.LoadingBasic
import com.example.papper.features.storage.storages.view.success_data.FloatingBtn
import com.example.papper.features.storage.storages.view.success_data.SuccessBasic
import com.example.papper.navigation.ChatsScreen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun StoragesBasic(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
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
        },
    ) {
        Scaffold (
            topBar = {
                TopBar(modifier = modifier, navHostController = navHostController)
            },
            floatingActionButton = {
                if (viewModel.storagesScreenState.value == StoragesScreenState.Success) {
                    //ЭТО МЕСТО
                    if (navHostController.previousBackStackEntry?.destination?.route == ChatsScreen.toString()) {
                        FloatingBtn(
                            viewModel = viewModel,
                        )
                    }
                }
            },
            content = {
                when (viewModel.storagesScreenState.value) {
                    StoragesScreenState.Loading -> {
                        LoadingBasic(
                            modifier = Modifier.padding(it)
                        )
                    }
                    StoragesScreenState.Success -> {
                        SuccessBasic(
                            modifier = Modifier.padding(it),
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    }
                    StoragesScreenState.Error -> {
                        ErrorBasic(
                            modifier = Modifier.padding(it),
                            viewModel = viewModel,
                        )
                    }
                }
            },
        )
    }
}