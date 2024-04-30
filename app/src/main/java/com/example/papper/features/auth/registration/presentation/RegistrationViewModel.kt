package com.example.papper.features.auth.registration.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.account.SignUpUseCase
import com.example.papper.utils.AppDispatchers
import com.example.papper.utils.CheckRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel(), ContainerHost<RegistrationState, RegistrationSideEffects> {

    override val container = container<RegistrationState, RegistrationSideEffects>(RegistrationState())

    val registrationScreenState = mutableStateOf<RegistrationScreenState>(RegistrationScreenState.TypingLogin)
    val allFieldScreenState = mutableStateOf<AllFieldsScreenState>(AllFieldsScreenState.Default)
    val validateLogin = mutableStateOf(false)
    val validatePassword = mutableStateOf(false)

//    fun updateName(name: String) = intent {
//        reduce {
//            state.copy(name = name)
//        }
//    }
//
//    fun toSurname() = intent {
//        postSideEffect(sideEffect = RegistrationSideEffects.toSurname)
//    }
//
//    fun updateSurname(surname: String) = intent {
//        reduce {
//            state.copy(surname = surname)
//        }
//    }
//
//    fun toLogin() = intent {
//        postSideEffect(sideEffect = RegistrationSideEffects.toLogin)
//    }

    fun updateLogin(login: String) = intent {
        reduce {
            state.copy(login = login)
        }
        validateLogin.value = CheckRegistration.checkLogin(login)
    }

    fun toPassword() = intent {
        postSideEffect(sideEffect = RegistrationSideEffects.toPassword)
    }

    fun updatePassword(password: String) = intent {
        reduce {
            state.copy(password = password)
        }
        validatePassword.value = CheckRegistration.checkPassword(password)
    }

    fun toCode() = intent {
        postSideEffect(sideEffect = RegistrationSideEffects.toCode)
    }

    fun updateCode(code: String) = intent {
        reduce {
            state.copy(code = code)
        }
    }

    fun toAllFields() = intent {
        postSideEffect(sideEffect = RegistrationSideEffects.toAllFields)
    }

    fun createAccount() = intent {
        allFieldScreenState.value = AllFieldsScreenState.Loading
        val result = withContext(AppDispatchers.io) {
            signUpUseCase.execute(login = state.login, password = state.password, code = state.code)
        }

        if (result.isSuccess) {
            postSideEffect(RegistrationSideEffects.NavigateToChatsScreen)
        }
        else {
            allFieldScreenState.value = AllFieldsScreenState.Error
        }
    }

}