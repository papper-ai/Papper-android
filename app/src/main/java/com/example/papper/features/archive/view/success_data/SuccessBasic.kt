package com.example.papper.features.archive.view.success_data

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.archive.presentation.ArchivesViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: ArchivesViewModel,
) {
    Column(
        modifier = modifier,
    ) {
        ColumnOfChats(
            modifier = modifier,
            viewModel = viewModel,
            listOfChats = viewModel.collectAsState().value.listOfChats
        )
    }
}