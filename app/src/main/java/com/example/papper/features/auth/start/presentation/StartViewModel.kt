package com.example.papper.features.auth.start.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.account.CheckAccountDataUseCase
import com.example.papper.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val checkAccountDataUseCase: CheckAccountDataUseCase,
) : ViewModel(), ContainerHost<StartState, StartSideEffects> {

    override val container = container<StartState, StartSideEffects>(StartState())
    val startScreenState = mutableStateOf<StartScreenState>(StartScreenState.Loading)

    init {
        signIn()
    }

    private fun signIn() = intent {
        val result = withContext(AppDispatchers.io) {
            checkAccountDataUseCase.execute()
        }
        if (result.isSuccess) {
            postSideEffect(StartSideEffects.NavigateToChatsScreen)
        }
        else {
            startScreenState.value = StartScreenState.Default
        }
    }
}