package com.example.papper.utils


object CheckAuthFields {
    fun checkLogin(login: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9]{3,20}\$")
        return regex.matches(login)
    }

    fun checkPassword(password: String): String? {
        val lowercaseRegex = Regex("[a-z]")
        val uppercaseRegex = Regex("[A-Z]")
        val digitRegex = Regex("[0-9]")
        val specialRegex = Regex("[!@#\$%^&*()]")
        val latinRegex = Regex("^[a-zA-Z0-9!@#\$%^&*()]*$")

        var result: String? = ""
        val errors = mutableListOf<String>()

        if (!latinRegex.matches(password)) {
            errors.add("Пароль должен содержать только символы латиницы")
        }
        if (!lowercaseRegex.containsMatchIn(password)) {
            errors.add("Пароль должен содержать хотя бы 1 строчную букву")
        }
        if (!uppercaseRegex.containsMatchIn(password)) {
            errors.add("Пароль должен содержать хотя бы 1 заглавную букву")
        }
        if (!digitRegex.containsMatchIn(password)) {
            errors.add("Пароль должен содержать хотя бы 1 цифру")
        }
        if (!specialRegex.containsMatchIn(password)) {
            errors.add("Пароль должен содержать хотя бы 1 спец. символ: !@#\$%^&*()")
        }

        if (errors.isNotEmpty()) {
            for (error in errors) {
                result += "$error\n"
            }
            result = result?.dropLast(1)
        }

        return if (result != "") result else null
    }
}
