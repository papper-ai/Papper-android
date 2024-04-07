package com.example.papper.features.auth.sign_in.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.papper.base.BaseViewModel
import com.example.papper.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    // TODO: api для авторизации
    appState: MutableStateFlow<AppState>,
) : BaseViewModel<SignInSideEffects>(appState) {

    val signInScreenState = mutableStateOf<SignInScreenState>(SignInScreenState.Default)
    val fieldsStatus = mutableStateOf<Boolean>(false)

    fun typingLogin(text: String) = intent {
        reduce {
            state.value = state.value.copy(signInState = state.value.signInState.copy(login = text))
            state
        }
        checkField()
        Log.d("SignInViewModel", "typingLogin: Л:${state.value.signInState.login} П:${state.value.signInState.password}")
    }

    fun typingPassword(text: String) = intent {
        reduce {
            state.value = state.value.copy(signInState = state.value.signInState.copy(password = text))
            state
        }
        checkField()
        Log.d("SignInViewModel", "typingLogin: Л:${state.value.signInState.login} П:${state.value.signInState.password}")
    }

    fun forgotPasswordClick() = intent {
        //todo сделать call в api
    }

    fun signInClick() = intent {
        postSideEffect(SignInSideEffects.ShowLoadingState)
        //todo сделать call в api
    }

    private fun checkField() = intent {
        postSideEffect(
            SignInSideEffects.FieldsFilled(
                status = (state.value.signInState.login.isNotEmpty() && state.value.signInState.password.isNotEmpty())
            ),
        )
    }

}