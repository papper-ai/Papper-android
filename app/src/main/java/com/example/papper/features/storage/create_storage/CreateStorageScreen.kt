package com.example.papper.features.storage.create_storage

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.storage.create_storage.presentation.CreateStorageScreenState
import com.example.papper.features.storage.create_storage.presentation.CreateStorageSideEffects
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.navigation.Screens
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CreateStoragesScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    storagesViewModel: StoragesViewModel,
    navHostController: NavHostController,
) {
    val context = LocalContext.current
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            storagesViewModel = storagesViewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
            context = context,
        )
    }
    CreateStorageBasic(
        modifier = modifier,
        viewModel = viewModel,
        navHostController = navHostController,
    )
}

private fun handleSideEffect(
    viewModel: CreateStorageViewModel,
    storagesViewModel: StoragesViewModel,
    sideEffect: CreateStorageSideEffects,
    navHostController: NavHostController,
    context: Context,
) {
    when (sideEffect) {
        CreateStorageSideEffects.ShowChooseStorageType -> {
            viewModel.createStorageScreenState.value = CreateStorageScreenState.ChooseStorageType
        }
        CreateStorageSideEffects.ShowAttachFilesScreen -> {
            viewModel.createStorageScreenState.value = CreateStorageScreenState.AttachFiles
        }
        is CreateStorageSideEffects.NavigateToCreateChatScreen -> {
            navHostController.previousBackStackEntry?.savedStateHandle?.set(
                key = "storageId",
                value = sideEffect.id,
            )
            navHostController.popBackStack()
        }
        is CreateStorageSideEffects.NavigateToStorageScreen -> {
            storagesViewModel.loadData()
            navHostController.navigate(
                route = "${Screens.StorageScreen.route}/${sideEffect.id}",
            ) {
                popUpTo(Screens.CreateStorageScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
        is CreateStorageSideEffects.ShowErrorToast -> {
            Toast.makeText(context, context.getText(R.string.create_storage_error), Toast.LENGTH_SHORT).show()
        }

    }
}