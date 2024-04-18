package com.example.papper.features.auth.sign_in.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel(), ContainerHost<SignInState, SignInSideEffects> {

    override val container = container<SignInState, SignInSideEffects>(SignInState())

    val signInScreenState = mutableStateOf<SignInScreenState>(SignInScreenState.Default)
    val fieldsStatus = mutableStateOf<Boolean>(false)

    fun typingLogin(text: String) = intent {
        reduce {
            state.copy(login = text)
        }
        checkField()
    }

    fun typingPassword(text: String) = intent {
        reduce {
            state.copy(password = text)
        }
        checkField()
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
                status = (state.login.isNotEmpty() && state.password.isNotEmpty())
            ),
        )
    }

}