package com.example.papper.features.profile.presentation

data class ProfileState(
    val login: String = ""
)

sealed class ProfileScreenState {
    object Loading: ProfileScreenState()
    object Success: ProfileScreenState()
    object Error: ProfileScreenState()

}