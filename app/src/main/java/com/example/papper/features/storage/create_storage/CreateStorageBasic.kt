package com.example.papper.features.storage.create_storage

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.create_storage.presentation.CreateStorageScreenState
import com.example.papper.features.storage.create_storage.presentation.CreateStorageViewModel
import com.example.papper.features.storage.create_storage.view.TopBar
import com.example.papper.features.storage.create_storage.view.attach_files.AttachBasic
import com.example.papper.features.storage.create_storage.view.typing_title.TitleBasic

@Composable
fun CreateStorageBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateStorageViewModel,
    navHostController: NavHostController,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        when (viewModel.createStorageScreenState.value) {
            CreateStorageScreenState.TypingTitle -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        TitleBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                        )
                    }
                )
            }
            CreateStorageScreenState.AttachFiles -> {
                Scaffold(
                    topBar = {
                        TopBar(
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    },
                    content = {
                        AttachBasic(
                            modifier = modifier.padding(it),
                            viewModel = viewModel,
                            navHostController = navHostController,
                        )
                    }
                )

            }
        }
    }

}