package com.example.papper.features.auth.start.presentation

data class StartState(
    val a: Int = 0,
)

sealed class StartScreenState {
    object Loading : StartScreenState()
    object Default : StartScreenState()
    object Error : StartScreenState()
}