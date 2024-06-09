package com.example.papper.features.archive.view.success_data

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.archive.presentation.ArchivesViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SuccessBasic(
    modifier: Modifier = Modifier,
    viewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    Column(
        modifier = modifier,
    ) {
        ColumnOfChats(
            modifier = modifier,
            navHostController = navHostController,
            listOfChats = viewModel.collectAsState().value.listOfChats
        )
    }
}