package com.example.papper.features.storage.storages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.storages.presentation.StoragesScreenState
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import com.example.papper.features.storage.storages.view.TopBar
import com.example.papper.features.storage.storages.view.error_data.ErrorBasic
import com.example.papper.features.storage.storages.view.loading_data.LoadingBasic
import com.example.papper.features.storage.storages.view.success_data.FloatingBtn
import com.example.papper.features.storage.storages.view.success_data.SuccessBasic
import com.example.papper.navigation.Screens

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
                   TopBar(modifier = modifier, navHostController = navHostController)
               },
               content = {
                   LoadingBasic(
                       modifier = Modifier.padding(it)
                   )
               },
            )
        }

        StoragesScreenState.Success -> {
            when (navHostController.previousBackStackEntry?.destination?.route) {
                Screens.ChatsScreen.route -> {
                    Scaffold (
                        topBar = {
                            TopBar(modifier = modifier, navHostController = navHostController)
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
                Screens.CreateChatScreen.route -> {
                    Scaffold (
                        topBar = {
                            TopBar(modifier = modifier, navHostController = navHostController)
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
            }
        }

        StoragesScreenState.Error -> {
            Scaffold (
                topBar = {
                    TopBar(modifier = modifier, navHostController = navHostController)
                },
                content = {
                    ErrorBasic(
                        modifier = Modifier.padding(it),
                        viewModel = viewModel,
                        navHostController = navHostController
                    )
                },
            )
        }
    }
}