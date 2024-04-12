package com.example.papper.features.archive

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.archive.presentation.ArchivesScreenState
import com.example.papper.features.archive.presentation.ArchivesSideEffects
import com.example.papper.features.archive.presentation.ArchivesViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ArchivesScreen(
    modifier: Modifier = Modifier,
    viewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect
        )
    }
    ArchivesBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController
    )
}

private fun handleSideEffect(
    viewModel: ArchivesViewModel,
    sideEffect: ArchivesSideEffects
) {
    when (sideEffect) {
        ArchivesSideEffects.ShowError -> {
            viewModel.archivesScreenState.value = ArchivesScreenState.Error
        }
        ArchivesSideEffects.ShowLoading -> {
            viewModel.archivesScreenState.value = ArchivesScreenState.Loading
        }
        ArchivesSideEffects.ShowSuccess -> {
            viewModel.archivesScreenState.value = ArchivesScreenState.Success
        }

    }
}


