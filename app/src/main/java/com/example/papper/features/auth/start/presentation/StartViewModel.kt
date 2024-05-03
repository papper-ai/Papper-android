package com.example.papper.features.auth.start.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.account.CheckAccountDataUseCase
import com.example.papper.utils.AppDispatchers
import com.example.papper.utils.CheckNetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val checkNetworkStatus: CheckNetworkStatus,
    private val checkAccountDataUseCase: CheckAccountDataUseCase,
) : ViewModel(), ContainerHost<StartState, StartSideEffects> {

    override val container = container<StartState, StartSideEffects>(StartState())
    val startScreenState = mutableStateOf<StartScreenState>(StartScreenState.Loading)
    //val startScreenState = mutableStateOf<StartScreenState>(StartScreenState.Default)
    val showStartAnimation = mutableStateOf<Boolean>(false)

    init {
        viewModelScope.launch {
            delay(1)
            showStartAnimation.value = true
        }
        signIn()
    }

    private fun signIn() = intent {
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
                val result = withContext(AppDispatchers.io) {
                    checkAccountDataUseCase.execute()
                }
                showStartAnimation.value = false
                delay(500)
                if (result.isSuccess) {
                    postSideEffect(StartSideEffects.NavigateToChatsScreen)
                } else {
                    startScreenState.value = StartScreenState.Default
                }
            },
            onFail = {
                postSideEffect(StartSideEffects.ShowNetworkConnectionError)
            }

        )

    }
}