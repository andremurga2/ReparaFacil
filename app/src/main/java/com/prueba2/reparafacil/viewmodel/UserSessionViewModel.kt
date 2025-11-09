package com.prueba2.reparafacil.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserSessionViewModel : ViewModel() {
    var userName = mutableStateOf("")
    var userEmail = mutableStateOf("")
    var userType = mutableStateOf("") // "cliente" o "tecnico"

    fun setUserData(name: String, email: String, type: String) {
        userName.value = name
        userEmail.value = email
        userType.value = type
    }

    fun clearSession() {
        userName.value = ""
        userEmail.value = ""
        userType.value = ""
    }
}
