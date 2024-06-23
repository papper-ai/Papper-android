package com.example.papper.features.storage.create_file.view

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.papper.features.common.components.TopBarWithLogoComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.features.storage.create_file.view.confirm_creating.ConfirmExitAlertDialog
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    pagerState: PagerState,
) {
    var confirmExit by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    TopBarWithLogoComponent(
        modifier = modifier,
        onClick = {
            when (pagerState.currentPage) {
                0 -> {
                    viewModel.navigateBack()
                }
                1 -> {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
                2 -> {
                    confirmExit = true
                }
                3 -> {

                }
            }
        },
    )

    ConfirmExitAlertDialog(
        onDismiss = { confirmExit = false },
        showDialog = confirmExit,
        viewModel = viewModel,
        pagerState = pagerState
    )
}