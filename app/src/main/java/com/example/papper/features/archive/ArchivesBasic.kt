package com.example.papper.features.archive

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.archive.presentation.ArchivesScreenState
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.archive.view.TopBar
import com.example.papper.features.archive.view.error_data.ErrorBasic
import com.example.papper.features.archive.view.loading_data.LoadingBasic
import com.example.papper.features.archive.view.success_data.SuccessBasic
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ArchivesBasic(
    modifier: Modifier = Modifier,
    viewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(500)
            viewModel.refreshSata()
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = {
            refreshing = true
        },
        swipeEnabled = true,
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
            modifier = modifier
                .fillMaxSize(),
            topBar = {
                TopBar(
                    navHostController = navHostController,
                )
            },
            content = {
                when (viewModel.archivesScreenState.value) {
                    ArchivesScreenState.Loading -> {
                        Surface(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            LoadingBasic(
                                navHostController = navHostController,
                            )
                        }
                    }

                    ArchivesScreenState.Success -> {
                        Surface(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            SuccessBasic(
                                modifier = modifier
                                    .fillMaxSize(),
                                viewModel = viewModel,
                            )
                        }
                    }

                    ArchivesScreenState.Error -> {
                        Surface(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            ErrorBasic(
                                modifier = modifier,
                                viewModel = viewModel,
                                navHostController = navHostController,
                            )
                        }
                    }
                }
            },
        )
    }
}