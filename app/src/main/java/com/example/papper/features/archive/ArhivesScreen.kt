package com.example.papper.features.archive

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
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
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect,
            context = context,
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
    sideEffect: ArchivesSideEffects,
    context: Context,
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

        ArchivesSideEffects.ShowNetworkConnectionError -> {
            viewModel.archivesScreenState.value = ArchivesScreenState.Error
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
    }
}


