package com.example.papper.features.profile.presentation

sealed class ProfileSideEffects {
    object ShowLoading: ProfileSideEffects()
    object ShowError: ProfileSideEffects()
    object ShowSuccess: ProfileSideEffects()
    object ShowNetworkConnectionError: ProfileSideEffects()
    object NavigateToChatsScreen: ProfileSideEffects()
    object NavigateToStartScreen: ProfileSideEffects()
}