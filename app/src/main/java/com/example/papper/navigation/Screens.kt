package com.example.papper.navigation

import com.example.papper.utils.Constants

sealed class Screens(val route: String) {
    object StartScreen : Screens(route = Constants.START_SCREEN)
    object RegistrationScreen : Screens(route = Constants.REGISTRATION_SCREEN)
    object SignInScreen : Screens(route = Constants.SIGN_IN_SCREEN)
    object ChatsScreen : Screens(route = Constants.CHATS_SCREEN)
    object CreateChatScreen : Screens(route = Constants.CREATE_CHAT_SCREEN)
    object ChatScreen : Screens(route = Constants.CHAT_SCREEN)
    object StoragesScreen : Screens(route = Constants.STORAGES_SCREEN)
    object CreateStorageScreen : Screens(route = Constants.CREATE_STRORAGE_SCREEN)
    object StorageScreen : Screens(route = Constants.STORAGE_SCREEN)
    object ArchivesScreen : Screens(route = Constants.ARCHIVES_SCREEN)
    object ProfileScreen : Screens(route = Constants.PROFILE_SCREEN)
}