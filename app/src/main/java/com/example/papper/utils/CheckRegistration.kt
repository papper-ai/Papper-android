package com.example.papper.utils


object CheckRegistration {
    fun checkLogin(login: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9]{3,20}\$")
        return regex.matches(login)
    }

    fun checkPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()]).{8,20}\$")
        return  regex.matches(password)
    }
}
