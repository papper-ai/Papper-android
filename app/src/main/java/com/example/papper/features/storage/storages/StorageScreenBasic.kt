package com.example.papper.features.storage.storages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.presentation.StoragesScreenState
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.features.storage.storages.view.TopBar
import com.example.papper.features.storage.storages.view.loading_data.LoadingBasic
import com.example.papper.features.storage.storages.view.success_data.FloatingBtn
import com.example.papper.features.storage.storages.view.success_data.SuccessBasic

@Composable
fun StorageScreenBasic(
    modifier: Modifier = Modifier,
    viewModel: StoragesViewModel,
    navHostController: NavHostController,
) {
    when(viewModel.storagesScreenState.value) {
        StoragesScreenState.Loading -> {
            Scaffold (
               topBar = {
                   TopBar(modifier = modifier, viewModel = viewModel, navHostController = navHostController)
               },
               content = {
                   LoadingBasic(
                       modifier = Modifier.padding(it)
                   )
               },
            )
        }

        StoragesScreenState.Success -> {
            Scaffold (
                topBar = {
                    TopBar(modifier = modifier, viewModel = viewModel, navHostController = navHostController)
                },
                floatingActionButton = {
                    FloatingBtn(
                        navHostController = navHostController,
                    )
                },
                content = {
                    SuccessBasic(
                        modifier = Modifier.padding(it),
                        viewModel = viewModel,
                        navHostController = navHostController,
                    )
                },
            )
        }

        StoragesScreenState.Error -> {
            Scaffold (
                topBar = {
                    TopBar(modifier = modifier, viewModel = viewModel, navHostController = navHostController)
                },
                content = {
                    LoadingBasic(
                        modifier = Modifier.padding(it)
                    )
                },
            )
        }
    }
}