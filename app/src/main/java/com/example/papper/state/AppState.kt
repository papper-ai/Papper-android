package com.example.papper.state

import com.example.papper.features.archive.presentation.ArchivesState
import com.example.papper.features.auth.registration.presentation.RegistrationState
import com.example.papper.features.auth.sign_in.presentation.SignInState
import com.example.papper.features.chat.chat.presentation.ChatState
import com.example.papper.features.chat.chats.presentation.ChatsState
import com.example.papper.features.chat.create_chat.presentation.CreateChatState
import com.example.papper.features.storage.create_storage.presentation.CreateStorageState
import com.example.papper.features.storage.storage.presentation.StorageState
import com.example.papper.features.storage.storages.presentation.StoragesState

data class AppState(
    val signInState: SignInState = SignInState(),
    val registrationState: RegistrationState = RegistrationState(),
    val chatsState: ChatsState = ChatsState(),
    val createChatState: CreateChatState = CreateChatState(),
    val createStorageState: CreateStorageState = CreateStorageState(),
    val storagesState: StoragesState = StoragesState(),
    val archivesState: ArchivesState = ArchivesState(),
    val storageState: StorageState = StorageState(),
    val chatState: ChatState = ChatState(),
)