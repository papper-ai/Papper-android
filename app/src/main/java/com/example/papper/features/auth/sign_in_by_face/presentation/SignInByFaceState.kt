package com.example.papper.features.auth.sign_in_by_face.presentation

import android.graphics.Bitmap

data class SignInByFaceState(
    val list: List<Bitmap> = emptyList()
)