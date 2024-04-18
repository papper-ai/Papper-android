package com.example.papper.features.archive.view.error_data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.theme.dimens

@Composable
fun ErrorBasic(
    modifier: Modifier = Modifier,
    viewModel: ArchivesViewModel,
    navHostController: NavHostController,
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