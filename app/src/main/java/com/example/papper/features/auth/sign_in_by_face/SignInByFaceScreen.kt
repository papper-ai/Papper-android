package com.example.papper.features.auth.sign_in_by_face

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.auth.sign_in_by_face.presentation.SignInByFaceSideEffects
import com.example.papper.features.auth.sign_in_by_face.presentation.SignInByFaceViewModel
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.storage.storages.presentation.StoragesViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SignInByFaceScreen(
    viewModel: SignInByFaceViewModel,
    chatsViewModel: ChatsViewModel,
    storagesViewModel: StoragesViewModel,
    archivesViewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    viewModel.collectSideEffect { sideEffect ->
        handleSideEffects(
            sideEffect = sideEffect,
            viewModel = viewModel,
            chatsViewModel = chatsViewModel,
            storagesViewModel = storagesViewModel,
            archivesViewModel = archivesViewModel,
            navHostController = navHostController,
        )
    }

    SignInByFaceBasic(
        viewModel = viewModel,
    )
}

private fun handleSideEffects(
    sideEffect: SignInByFaceSideEffects,
    viewModel: SignInByFaceViewModel,
    chatsViewModel: ChatsViewModel,
    storagesViewModel: StoragesViewModel,
    archivesViewModel: ArchivesViewModel,
    navHostController: NavHostController,
) {
    when (sideEffect) {

        else -> {}
    }
}