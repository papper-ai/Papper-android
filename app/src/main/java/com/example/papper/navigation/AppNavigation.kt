package com.example.papper.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.papper.features.archive.ArchivesScreen
import com.example.papper.features.auth.registration.RegistrationScreen
import com.example.papper.features.auth.sign_in.SignInScreen
import com.example.papper.features.auth.start.StartScreen
import com.example.papper.features.chat.chat.ChatScreen
import com.example.papper.features.chat.chats.ChatsScreen
import com.example.papper.features.chat.create_chat.CreateChatScreen
import com.example.papper.features.profile.ProfileScreen
import com.example.papper.features.storage.create_storage.CreateStoragesScreen
import com.example.papper.features.storage.storage.StorageScreen
import com.example.papper.features.storage.storages.StoragesScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController
) {
    NavHost (
        navController = navHostController,
        startDestination = Screens.StartScreen.route
        //startDestination = Screens.ChatsScreen.route
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
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(route = Screens.CreateChatScreen.route) { entry ->
            val id = entry.savedStateHandle.get<String>("storageId")
            CreateChatScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
                id = id,
            )
        }
        composable(
            route = "${Screens.ChatScreen.route}/{chatId}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.StringType }
            )
        ) {
            ChatScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
                id = it.arguments?.getString("chatId") ?: throw Exception("Id not found")
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
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}