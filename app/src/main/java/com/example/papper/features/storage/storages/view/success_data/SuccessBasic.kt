package com.example.papper.features.storage.storages.view.success_data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.theme.dimens
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
    navHostController: NavHostController,
) {
    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(1000)
            viewModel.loadData()
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = {
            refreshing = true
        },
        indicatorPadding = PaddingValues(top = 55.dp),
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
        Column(
            modifier = modifier
                .padding(top = MaterialTheme.dimens.bottomGap)
                .fillMaxSize()
        ) {
            ColumnOfStorages(
                navHostController = navHostController,
                viewModel = viewModel,
                list = viewModel.collectAsState().value.listOfStorages,
            )
        }
    }
}