package com.example.papper.features.auth.sign_in.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.account.SignInUseCase
import com.example.papper.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel(), ContainerHost<SignInState, SignInSideEffects> {

    override val container = container<SignInState, SignInSideEffects>(SignInState())

    val signInScreenState = mutableStateOf<SignInScreenState>(SignInScreenState.Default)
    val fieldsStatus = mutableStateOf<Boolean>(false)

    fun typingLogin(text: String) = intent {
        if (signInScreenState.value is SignInScreenState.Error) {signInScreenState.value = SignInScreenState.Default}
        reduce {
            state.copy(login = text)
        }
        checkField()
    }

    fun typingPassword(text: String) = intent {
        if (signInScreenState.value is SignInScreenState.Error) {signInScreenState.value = SignInScreenState.Default}
        reduce {
            state.copy(password = text)
        }
        checkField()
    }

    fun forgotPasswordClick() = intent {
        //todo сделать call в api
    }

    fun signIn() = intent {
        postSideEffect(SignInSideEffects.ShowLoadingState)
        val result = withContext(AppDispatchers.io) {
            signInUseCase.execute(login = state.login, password = state.password)
        }
        if (result.isSuccess) {
            postSideEffect(SignInSideEffects.NavigateToChatsScreen)
        }
        else {
            postSideEffect(SignInSideEffects.ShowErrorState(result.msg))
        }
    }

    private fun checkField() = intent {
        postSideEffect(
            SignInSideEffects.FieldsFilled(
                status = (state.login.isNotEmpty() && state.password.isNotEmpty())
            ),
        )
    }

}