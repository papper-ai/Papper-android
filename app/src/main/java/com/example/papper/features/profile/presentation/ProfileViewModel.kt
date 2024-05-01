package com.example.papper.features.profile.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.account.GetLoginUseCase
import com.example.domain.usecases.account.LogOutUseCase
import com.example.papper.utils.AppDispatchers
import com.example.papper.utils.CheckNetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val networkStatus: CheckNetworkStatus,
    private val getUserLoginUseCase: GetLoginUseCase,
    private val logOutUseCase: LogOutUseCase,
): ContainerHost<ProfileState, ProfileSideEffects>, ViewModel() {

    override val container = container<ProfileState, ProfileSideEffects>(ProfileState())
    val profileScreenState = mutableStateOf<ProfileScreenState>(ProfileScreenState.Loading)

    init {
        getLogin()
    }

    fun getLogin() = intent {
        postSideEffect(ProfileSideEffects.ShowLoading)
        if (networkStatus.isNetworkAvailable()) {
            val result = withContext(AppDispatchers.io) {
                getUserLoginUseCase.execute()
            }
            if (result.isSuccess) {
                reduce {
                    state.copy(login = result.login)
                }
                postSideEffect(ProfileSideEffects.ShowSuccess)
                profileScreenState.value = ProfileScreenState.Success
            } else {
                postSideEffect(ProfileSideEffects.ShowError)
                profileScreenState.value = ProfileScreenState.Error
            }
        } else {
            postSideEffect(ProfileSideEffects.ShowNetworkConnectionError)
        }

    }

    fun logOut() = intent {
        withContext(AppDispatchers.io) {
            logOutUseCase.execute()
        }
        postSideEffect(ProfileSideEffects.NavigateToStartScreen)
    }

    fun navigateToChatsScreen() = intent {
        postSideEffect(ProfileSideEffects.NavigateToChatsScreen)
    }

}