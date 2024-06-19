package com.example.papper.features.auth.sign_in_by_face.presentation

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignInByFaceViewModel @Inject constructor(

): ViewModel(), ContainerHost<SignInByFaceState, SignInByFaceSideEffects> {

    override val container = container<SignInByFaceState, SignInByFaceSideEffects>(SignInByFaceState())


    fun addItem(bitmap: Bitmap) = intent {
        reduce {
            state.copy(list = state.list.plus(bitmap))
        }
    }

}