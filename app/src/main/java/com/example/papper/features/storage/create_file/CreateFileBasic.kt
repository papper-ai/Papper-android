package com.example.papper.features.storage.create_file

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.papper.features.storage.create_file.presentation.CreateFileScreenState
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import com.example.papper.features.storage.create_file.view.TopBar
import com.example.papper.features.storage.create_file.view.attach_photos.AttachPhotoBasic
import com.example.papper.features.storage.create_file.view.typing_title.TitleBasic

@Composable
fun CreateFileBasic(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    navHostController: NavHostController,
) {
    Scaffold (
        topBar = {
            TopBar(viewModel = viewModel, navHostController = navHostController)
        },
        content = {
            when (viewModel.createFileScreenState.value) {
                CreateFileScreenState.TypingTitle -> {
                    TitleBasic (
                        modifier = modifier.padding(it),
                        viewModel = viewModel,
                    )
                }
                CreateFileScreenState.AttachPhotos -> {
                    AttachPhotoBasic (
                        modifier = modifier.padding(it),
                        viewModel = viewModel,
                        navHostController = navHostController,
                    )
                }
                CreateFileScreenState.ConfirmCreating -> {

                }
                CreateFileScreenState.Error -> {

                }

            }
        },
    )
}