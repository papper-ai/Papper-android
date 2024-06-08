package com.example.papper.features.storage.storage

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.papper.R
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.storage.storage.presentation.StorageScreenState
import com.example.papper.features.storage.storage.presentation.StorageSideEffects
import com.example.papper.features.storage.storage.presentation.StorageViewModel
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.navigation.Screens
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StorageScreen(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel,
    storagesViewModel: StoragesViewModel,
    chatsViewModel: ChatsViewModel,
    navHostController: NavHostController,
    id: String,
) {
    val context = LocalContext.current

    viewModel.id = id

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            viewModel = viewModel,
            storagesViewModel = storagesViewModel,
            chatsViewModel = chatsViewModel,
            sideEffect = sideEffect,
            navHostController = navHostController,
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
    storagesViewModel: StoragesViewModel,
    chatsViewModel: ChatsViewModel,
    sideEffect: StorageSideEffects,
    navHostController: NavHostController,
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
        is StorageSideEffects.ShowToastDeleteFileError -> {
            Toast.makeText(context, "${context.getText(R.string.file_delete_error)} ${sideEffect.title}", Toast.LENGTH_LONG).show()
        }
        StorageSideEffects.ShowRenameErrorToast -> {
            Toast.makeText(context, context.getText(R.string.rename_title_error), Toast.LENGTH_LONG).show()
        }
        StorageSideEffects.NavigateToStoragesScreen -> {
            navHostController.popBackStack()
        }
        is StorageSideEffects.DeleteStorageAndNavigateToStoragesScreen -> {
            storagesViewModel.deleteStorage(id = sideEffect.id)
            chatsViewModel.loadData()
            navHostController.navigate(
                Screens.ChatsScreen.route,
            ) {
                popUpTo(navHostController.previousBackStackEntry?.destination?.route ?: "") {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
        StorageSideEffects.ShowToastDeleteStorageError -> {
            Toast.makeText(context, context.getText(R.string.delete_storage_error), Toast.LENGTH_LONG).show()
        }
        is StorageSideEffects.RenameStorage -> {
            //storagesViewModel.loadData()
            storagesViewModel.reneameStorage(id = sideEffect.id, title = sideEffect.title)
        }

        StorageSideEffects.ShowNetworkConnectionError -> {
            Toast.makeText(context, context.getText(R.string.network_connection_error), Toast.LENGTH_LONG).show()
        }

        StorageSideEffects.NavigateToCreateFileScreen -> {
            navHostController.navigate(Screens.CreateFileScreen.route)
        }
    }
}