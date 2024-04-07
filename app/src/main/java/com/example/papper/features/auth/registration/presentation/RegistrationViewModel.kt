package com.example.papper.features.auth.registration.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.papper.base.BaseViewModel
import com.example.papper.navigation.Screens
import com.example.papper.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
) : BaseViewModel<RegistrationSideEffects>(appState) {

    val registrationScreenState = mutableStateOf<RegistrationScreenState>(RegistrationScreenState.TypingName)
    val allFieldScreenState = mutableStateOf<AllFieldsScreenState>(AllFieldsScreenState.Default)

    fun updateName(name: String) = intent {
        reduce {
            state.value = state.value.copy(registrationState = state.value.registrationState.copy(name = name))
            state
        }
    }

    fun toSurname() = intent {
        postSideEffect(sideEffect = RegistrationSideEffects.toSurname)
    }

    fun updateSurname(surname: String) = intent {
        reduce {
            state.value = state.value.copy(registrationState = state.value.registrationState.copy(surname = surname))
            state
        }
    }

    fun toLogin() = intent {
        postSideEffect(sideEffect = RegistrationSideEffects.toLogin)
    }

    fun updateLogin(login: String) = intent {
        reduce {
            state.value = state.value.copy(registrationState = state.value.registrationState.copy(login = login))
            state
        }
    }

    fun toPassword() = intent {
        postSideEffect(sideEffect = RegistrationSideEffects.toPassword)
    }

    fun updatePassword(password: String) = intent {
        reduce {
            state.value = state.value.copy(registrationState = state.value.registrationState.copy(password = password))
            state
        }
    }

    fun toCode() = intent {
        postSideEffect(sideEffect = RegistrationSideEffects.toCode)
    }

    fun updateCode(code: String) = intent {
        reduce {
            state.value = state.value.copy(registrationState = state.value.registrationState.copy(code = code))
            state
        }
    }

    fun toAllFields() = intent {
        postSideEffect(sideEffect = RegistrationSideEffects.toAllFields)
    }

    fun createAccount(navHostController: NavHostController) {
        viewModelScope.launch {
            allFieldScreenState.value = AllFieldsScreenState.Loading
            delay(2000)
            Log.d("test", "RegistrationBtn: попал")
            navHostController.navigate(
                Screens.ChatsScreen.route,
            ) {
                popUpTo(Screens.RegistrationScreen.route) {
                    inclusive = true
                }
                popUpTo(Screens.StartScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

}