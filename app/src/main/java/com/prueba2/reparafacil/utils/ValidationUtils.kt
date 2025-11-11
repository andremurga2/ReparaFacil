package com.prueba2.reparafacil.utils

object ValidationUtils {

    fun isValidName(name: String): Boolean {
        return name.trim().length >= 2
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhone(phone: String): Boolean {
        // Acepta números con o sin prefijo +56
        val regex = Regex("^((\\+?56)?\\s?9\\d{8})$")
        return regex.matches(phone)
    }

    fun isValidPassword(password: String): Boolean {
        // Debe tener al menos 6 caracteres, una mayúscula y un número
        val regex = Regex("^(?=.*[A-Z])(?=.*\\d).{6,}$")
        return regex.matches(password)
    }

    fun isNotEmpty(value: String?): Boolean {
        return !value.isNullOrBlank()
    }

    fun isMatchingPasswords(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}
