package com.example.data.datasource.local

import android.content.Context
import com.example.data.utils.Constants
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(
    private val context: Context,
) {
    fun saveSuccessToken(token: String) {
        context.getSharedPreferences(Constants.AUTH_SHARED_PREF, Context.MODE_PRIVATE).edit()
            .putString(Constants.JWT_SUCCESS_TOKEN, token)
            .apply()
    }

    fun saveRefreshToken(token: String) {
        context.getSharedPreferences(Constants.AUTH_SHARED_PREF, Context.MODE_PRIVATE).edit()
            .putString(Constants.JWT_REFRESH_TOKEN, token)
            .apply()
    }

    fun getJwtToken(): String? = context.getSharedPreferences(Constants.AUTH_SHARED_PREF, Context.MODE_PRIVATE).getString(Constants.JWT_SUCCESS_TOKEN, "")

    fun getRefreshToken(): String? = context.getSharedPreferences(Constants.AUTH_SHARED_PREF, Context.MODE_PRIVATE).getString(Constants.JWT_REFRESH_TOKEN, "")

    fun saveLogin(login: String) {
        context.getSharedPreferences(Constants.AUTH_SHARED_PREF, Context.MODE_PRIVATE).edit()
            .putString(Constants.LOGIN, login)
            .apply()
    }

    fun getLogin(): String? = context.getSharedPreferences(Constants.AUTH_SHARED_PREF, Context.MODE_PRIVATE).getString(Constants.LOGIN, "")

    fun savePassword(password: String) {
        context.getSharedPreferences(Constants.AUTH_SHARED_PREF, Context.MODE_PRIVATE).edit()
            .putString(Constants.PASSWORD, password)
            .apply()
    }

    fun getPassword(): String? = context.getSharedPreferences(Constants.AUTH_SHARED_PREF, Context.MODE_PRIVATE).getString(Constants.PASSWORD, "")
}