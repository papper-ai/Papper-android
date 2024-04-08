package com.example.papper.state

import com.example.papper.features.auth.registration.presentation.RegistrationState
import com.example.papper.features.auth.sign_in.presentation.SignInState
import com.example.papper.features.chat.chats.presentation.ChatsState
import com.example.papper.features.chat.create_chat.presentation.CreateChatState

data class AppState(
    val signInState: SignInState = SignInState(),
    val registrationState: RegistrationState = RegistrationState(),
    val chatsState: ChatsState = ChatsState(),
    val createChatState: CreateChatState = CreateChatState(),
)