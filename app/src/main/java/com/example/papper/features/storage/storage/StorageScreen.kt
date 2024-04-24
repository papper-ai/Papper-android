package com.example.papper.features.storage.storage

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.storage.storage.presentation.StorageScreenState
import com.example.papper.features.storage.storage.presentation.StorageSideEffects
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StorageScreen(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    navHostController: NavHostController,
    id: String,
) {
    val context = LocalContext.current

    viewModel.id = id

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            sideEffect = sideEffect,
            context = context,
        )
    }
    StorageBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController,
    )
}

private fun handleSideEffect(
    viewModel: StorageViewModel,
    sideEffect: StorageSideEffects,
    context: Context,
) {
    when (sideEffect) {
        StorageSideEffects.ShowLoading -> {
            if (viewModel.storageScreenState.value !is StorageScreenState.Loading)
                viewModel.storageScreenState.value = StorageScreenState.Loading
        }
        StorageSideEffects.ShowSuccess -> {
            viewModel.storageScreenState.value = StorageScreenState.Success
        }
        StorageSideEffects.ShowError -> {
            viewModel.storageScreenState.value = StorageScreenState.Error
        }

        is StorageSideEffects.ShowToastAddFileError -> {
            Toast.makeText(context, "${context.getText(R.string.file_add_error)} ${sideEffect.title}", Toast.LENGTH_LONG).show()
        }
        is StorageSideEffects.ShowToastFileAlreadyExist -> {
            Toast.makeText(context, "${sideEffect.title} ${context.getText(R.string.file_already_exitst)}", Toast.LENGTH_LONG).show()
        }
    }
}