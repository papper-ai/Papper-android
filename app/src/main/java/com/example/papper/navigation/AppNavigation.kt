package com.example.papper.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.papper.features.archive.ArchivesScreen
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.auth.registration.RegistrationScreen
import com.example.papper.features.auth.sign_in.SignInScreen
import com.example.papper.features.auth.sign_in_by_face.SignInByFaceBasic
import com.example.papper.features.auth.sign_in_by_face.SignInByFaceScreen
import com.example.papper.features.auth.start.StartScreen
import com.example.papper.features.chat.chat.ChatScreen
import com.example.papper.features.chat.chats.ChatsScreen
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.chat.create_chat.CreateChatScreen
import com.example.papper.features.profile.ProfileScreen
import com.example.papper.features.storage.create_file.CreateFileScreen
import com.example.papper.features.storage.create_storage.CreateStoragesScreen
import com.example.papper.features.storage.storage.StorageScreen
import com.example.papper.features.storage.storages.StoragesScreen
import com.example.papper.features.storage.storages.presentation.StoragesViewModel

@Composable
fun AppNavigation(
    navHostController: NavHostController
) {
    val chatsViewModel: ChatsViewModel = hiltViewModel()
    val archivesViewModel: ArchivesViewModel = hiltViewModel()
    val storagesViewModel: StoragesViewModel = hiltViewModel()

    NavHost (
        navController = navHostController,
        //startDestination = Screens.StartScreen.route,
        startDestination = StartScreen,
        //startDestination = Screens.CreateFileScreen.route,
        enterTransition = { fadeIn(tween(300)) },
        exitTransition = { fadeOut(tween(300)) },
        popEnterTransition = { fadeIn(tween(300)) },
        popExitTransition = { fadeOut(tween(300)) },
    ) {
        composable<StartScreen> {
            StartScreen(
                viewModel = hiltViewModel(),
                chatsViewModel = chatsViewModel,
                storagesViewModel = storagesViewModel,
                archivesViewModel = archivesViewModel,
                navHostController = navHostController,
            )
        }
        composable<RegistrationScreen> {
            RegistrationScreen(
                viewModel = hiltViewModel(),
                chatsViewModel = chatsViewModel,
                storagesViewModel = storagesViewModel,
                archivesViewModel = archivesViewModel,
                navHostController = navHostController,
            )
        }
        composable<SignInScreen> {
            SignInScreen(
                viewModel = hiltViewModel(),
                chatsViewModel = chatsViewModel,
                storagesViewModel = storagesViewModel,
                archivesViewModel = archivesViewModel,
                navHostController = navHostController,
            )
        }
        composable<ChatsScreen> {
            ChatsScreen(
                viewModel = chatsViewModel,
                navHostController = navHostController,
            )
        }
        composable<CreateChatScreen> { entry ->
            val args = entry.toRoute<CreateChatScreen>()
            val id = entry.savedStateHandle.get<String>("storageId")
            CreateChatScreen(
                viewModel = hiltViewModel(),
                chatsViewModel = chatsViewModel,
                navHostController = navHostController,
                id = id,
            )
        }
        composable<ChatScreen> { entry ->
            val args = entry.toRoute<ChatScreen>()
            ChatScreen(
                viewModel = hiltViewModel(),
                chatsViewModel = chatsViewModel,
                archivesViewModel = archivesViewModel,
                navHostController = navHostController,
                id = args.chatId,
            )
        }
        composable<StoragesScreen> {
            StoragesScreen(
                viewModel = storagesViewModel,
                navHostController = navHostController,
            )
        }
        composable<CreateStorageScreen> {
            CreateStoragesScreen(
                viewModel = hiltViewModel(),
                storagesViewModel = storagesViewModel,
                navHostController = navHostController,
            )
        }
        composable<StorageScreen> {entry ->
            val args = entry.toRoute<StorageScreen>()
            StorageScreen(
                viewModel = hiltViewModel(),
                storagesViewModel = storagesViewModel,
                chatsViewModel = chatsViewModel,
                navHostController = navHostController,
                id = args.storageId
            )
        }
        composable<ArchivesScreen> {
            ArchivesScreen(
                viewModel = archivesViewModel,
                navHostController = navHostController,
            )
        }
        composable<ProfileScreen> {
            ProfileScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable<CreateFileScreen> {
            CreateFileScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable<SignInByFaceScreen> {
            SignInByFaceScreen(
                viewModel = hiltViewModel(),
                chatsViewModel = chatsViewModel,
                storagesViewModel = storagesViewModel,
                archivesViewModel = archivesViewModel,
                navHostController = navHostController,
            )
        }
    }
}