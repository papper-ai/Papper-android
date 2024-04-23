package com.example.papper.features.chat.create_chat.view.choose_storage

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.chat.create_chat.presentation.ChooseStorageScreenState
import com.example.papper.features.chat.create_chat.presentation.CreateChatViewModel
import com.example.papper.features.chat.create_chat.view.choose_storage.attach_files.AttachFilesBasic
import com.example.papper.features.chat.create_chat.view.choose_storage.choose_variable.ChooseVariableBasic
import com.example.papper.features.chat.create_chat.view.loading.LoadingBasic

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ChooseStorageBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateChatViewModel,
    navHostController: NavHostController,
) {
    when (viewModel.chooseStorageScreenState.value) {
        ChooseStorageScreenState.ChooseVariable -> {
            ChooseVariableBasic(
                modifier = modifier,
                viewModel = viewModel,
                navHostController = navHostController,
            )
        }

        ChooseStorageScreenState.ListOfFiles -> {
            AttachFilesBasic(
                modifier = modifier,
                viewModel = viewModel,
                navHostController = navHostController,
            )
        }

        ChooseStorageScreenState.Loading -> {
            LoadingBasic(
                viewModel = viewModel,
                navHostController = navHostController,
            )
        }
    }

}