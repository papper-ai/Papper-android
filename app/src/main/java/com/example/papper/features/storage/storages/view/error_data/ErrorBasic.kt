package com.example.papper.features.storage.storages.view.error_data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.theme.dimens

@Composable
fun ErrorBasic(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        ErrorMsg()
        Box(
            modifier = modifier
                .padding(bottom = MaterialTheme.dimens.bottomGap3),
            contentAlignment = Alignment.BottomCenter
        ) {
            ReloadBtn(viewModel = viewModel)
        }
    }
}