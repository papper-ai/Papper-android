package com.example.papper.features.storage.storages

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.storage.storages.presentation.StoragesScreenState
import com.example.papper.features.storage.storages.presentation.StoragesSideEffects
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.navigation.CreateStorageScreen
import com.example.papper.navigation.StorageScreen
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StoragesScreen(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            viewModel = viewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
            context = context,
        )
    }

    StoragesBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController,
    )
}

private fun handleSideEffects(
    viewModel: StoragesViewModel,
    sideEffect: StoragesSideEffects,
    navHostController: NavHostController,
    context: Context,
) {
    when(sideEffect) {
        StoragesSideEffects.ShowLoading -> {
            viewModel.storagesScreenState.value = StoragesScreenState.Loading
        }
        StoragesSideEffects.ShowSuccess -> {
            viewModel.storagesScreenState.value = StoragesScreenState.Success
        }
        StoragesSideEffects.ShowError -> {
            viewModel.storagesScreenState.value = StoragesScreenState.Error
        }
        is StoragesSideEffects.NavigateToCreateChatScreen -> {
            navHostController.previousBackStackEntry?.savedStateHandle?.set("storageId", sideEffect.id)
            navHostController.popBackStack()
        }
        is StoragesSideEffects.NavigateToStorageScreen -> {
            navHostController.navigate(StorageScreen(storageId = sideEffect.id))
        }
        StoragesSideEffects.NavigateToCreateStorageScreen -> {
            navHostController.navigate(CreateStorageScreen)
        }
        StoragesSideEffects.ShowNetworkConnectionError -> {
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_SHORT).show()
        }
    }
}