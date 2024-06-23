package com.example.papper.features.storage.create_file.view.confirm_creating

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.theme.dimens
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ConfirmCreatingBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    var confirmExit by remember {
        mutableStateOf(false)
    }

    BackHandler {
        confirmExit = true
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap3))
        ConfirmConvertText()
        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap2))
        ConvertedPhotoPager(
            viewModel = viewModel,
            list = viewModel.collectAsState().value.listOfPhotos,
            onEmptyList = { coroutineScope.launch { pagerState.animateScrollToPage(1) } },
        )
        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimens.bottomGap3))
    }

    ConfirmExitAlertDialog(
        onDismiss = { confirmExit = false },
        showDialog = confirmExit,
        viewModel = viewModel,
        pagerState = pagerState,
    )
}