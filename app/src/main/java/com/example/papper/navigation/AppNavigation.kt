package com.example.papper.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.papper.features.archive.ArchivesScreen
import com.example.papper.features.auth.registration.RegistrationScreen
import com.example.papper.features.auth.sign_in.SignInScreen
import com.example.papper.features.auth.start.StartScreen
import com.example.papper.features.chat.chat.ChatScreen
import com.example.papper.features.chat.chats.ChatsScreen
import com.example.papper.features.chat.create_chat.CreateChatScreen
import com.example.papper.features.profile.ProfileScreen
import com.example.papper.features.storage.create_storage.CreateStoragesScreen
import com.example.papper.features.storage.StorageScreen
import com.example.papper.features.storage.StoragesScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController
) {
    NavHost (
        navController = navHostController,
        //startDestination = Screens.StartScreen.route
        startDestination = Screens.CreateStorageScreen.route
    ) {
        composable(route = Screens.StartScreen.route) {
            StartScreen(
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
        composable(route = Screens.CreateChatScreen.route) {
            CreateChatScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(route = Screens.ChatScreen.route) {
            ChatScreen()
        }
        composable(route = Screens.StoragesScreen.route) {
            StoragesScreen()
        }
        composable(route = Screens.CreateStorageScreen.route) {
            CreateStoragesScreen(
                viewModel = hiltViewModel(),
                navHostController = navHostController,
            )
        }
        composable(route = Screens.StorageScreen.route) {
            StorageScreen()
        }
        composable(route = Screens.ArchivesScreen.route) {
            ArchivesScreen()
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}