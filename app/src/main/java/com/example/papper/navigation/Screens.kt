package com.example.papper.navigation

import kotlinx.serialization.Serializable

//sealed class Screens(val route: String) {
//    object StartScreen : Screens(route = Constants.START_SCREEN)
//    object RegistrationScreen : Screens(route = Constants.REGISTRATION_SCREEN)
//    object SignInScreen : Screens(route = Constants.SIGN_IN_SCREEN)
//    object ChatsScreen : Screens(route = Constants.CHATS_SCREEN)
//    object CreateChatScreen : Screens(route = Constants.CREATE_CHAT_SCREEN)
//    object ChatScreen : Screens(route = Constants.CHAT_SCREEN)
//    object StoragesScreen : Screens(route = Constants.STORAGES_SCREEN)
//    object CreateStorageScreen : Screens(route = Constants.CREATE_STRORAGE_SCREEN)
//    object StorageScreen : Screens(route = Constants.STORAGE_SCREEN)
//    object ArchivesScreen : Screens(route = Constants.ARCHIVES_SCREEN)
//    object ProfileScreen : Screens(route = Constants.PROFILE_SCREEN)
//    object CreateFileScreen : Screens(route = Constants.CREATE_FILE_SCREEN)
//    object SignInByFaceScreen : Screens(route = Constants.SIGN_IN_BY_FACE_SCREEN)
//}

@Serializable
object StartScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object RegistrationScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object SignInScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object ChatsScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
data class CreateChatScreen(
    val storageId: String? = null,
) {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
data class ChatScreen(
    val chatId: String,
) {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object StoragesScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object CreateStorageScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
data class StorageScreen(
    val storageId: String,
) {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object ArchivesScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object ProfileScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object CreateFileScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}

@Serializable
object SignInByFaceScreen {
    override fun toString(): String {
        return super.toString().substringBefore("@")
    }
}