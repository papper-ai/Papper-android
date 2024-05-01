package com.example.papper.features.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.papper.features.profile.presentation.ProfileScreenState
import com.example.papper.features.profile.presentation.ProfileViewModel
import com.example.papper.features.profile.view.TopBar
import com.example.papper.features.profile.view.error_data.ErrorBasic
import com.example.papper.features.profile.view.success_data.SuccessBasic
import com.example.papper.features.storage.storages.view.loading_data.LoadingBasic

@Composable
fun ProfileBasic(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
) {
    when (viewModel.profileScreenState.value) {
        ProfileScreenState.Loading -> {
            Scaffold(
                topBar = {
                    TopBar(
                        viewModel = viewModel,
                    )
                },
                content = {
                    LoadingBasic(
                        modifier = Modifier.padding(it),
                    )
                }
            )
        }
        ProfileScreenState.Success -> {
            Scaffold(
                topBar = {
                    TopBar(
                        viewModel = viewModel,
                    )
                },
                content = {
                    SuccessBasic(
                        modifier = Modifier.padding(it),
                        viewModel = viewModel
                    )
                }
            )
        }
        ProfileScreenState.Error -> {
            Scaffold(
                topBar = {
                    TopBar(
                        viewModel = viewModel,
                    )
                },
                content = {
                    ErrorBasic(
                        modifier = Modifier.padding(it),
                        viewModel = viewModel
                    )
                }
            )
        }
    }
}