package com.example.papper.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
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
import com.example.papper.features.archive.ArchivesScreen
import com.example.papper.features.archive.presentation.ArchivesViewModel
import com.example.papper.features.auth.registration.RegistrationScreen
import com.example.papper.features.auth.sign_in.SignInScreen
import com.example.papper.features.auth.start.StartScreen
import com.example.papper.features.chat.chat.ChatScreen
import com.example.papper.features.chat.chats.ChatsScreen
import com.example.papper.features.chat.chats.presentation.ChatsViewModel
import com.example.papper.features.chat.create_chat.CreateChatScreen
import com.example.papper.features.profile.ProfileScreen
import com.example.papper.features.storage.create_storage.CreateStoragesScreen
import com.example.papper.features.storage.storage.StorageScreen
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.features.storage.storages.StoragesScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController
) {
    val chatsViewModel: ChatsViewModel = hiltViewModel()
    val archivesViewModel: ArchivesViewModel = hiltViewModel()

    NavHost (
        navController = navHostController,
        startDestination = Screens.StartScreen.route,
        //startDestination = Screens.ChatsScreen.route
        enterTransition = { fadeIn(tween(300)) },
        exitTransition = { fadeOut(tween(300)) },
        popEnterTransition = { fadeIn(tween(300)) },
        popExitTransition = { fadeOut(tween(300)) },
    ) {
        composable(route = Screens.StartScreen.route) {
            StartScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(route = Screens.RegistrationScreen.route) {
            RegistrationScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(route = Screens.ChatsScreen.route) {
            ChatsScreen(
                viewModel = chatsViewModel,
                navHostController = navHostController,
            )
        }
        composable(route = Screens.CreateChatScreen.route) { entry ->
            val id = entry.savedStateHandle.get<String>("storageId")
            CreateChatScreen(
                viewModel = hiltViewModel(),
                chatsViewModel = chatsViewModel,
                navHostController = navHostController,
                id = id,
            )
        }
        composable(
            route = "${Screens.ChatScreen.route}/{chatId}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.StringType }
            )
        ) { entry ->
            ChatScreen(
                viewModel = hiltViewModel(),
                chatsViewModel = chatsViewModel,
                archivesViewModel = archivesViewModel,
                navHostController = navHostController,
                id = entry.arguments?.getString("chatId") ?: throw Exception("Id not found"),
                file = entry.savedStateHandle.get<FilePresentationModel>("file"),
            )
        }
        composable(route = Screens.StoragesScreen.route) {
            StoragesScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(
            route = Screens.CreateStorageScreen.route,
        ) {
            CreateStoragesScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(
            route = "${Screens.StorageScreen.route}/{storageId}",
            arguments = listOf(
                navArgument("storageId") { type = NavType.StringType }
            )
        ) {
            StorageScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
                id = it.arguments?.getString("storageId") ?: throw Exception("Id not found")
            )
        }
        composable(route = Screens.ArchivesScreen.route) {
            ArchivesScreen(
                viewModel = archivesViewModel,
                navHostController = navHostController,
            )
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
    }
}