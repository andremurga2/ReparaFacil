package com.prueba2.reparafacil.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * ViewModel que gestiona la sesión del usuario en la aplicación.
 * Mantiene datos persistentes durante la navegación dentro del ciclo de vida del ViewModel.
 */
class UserSessionViewModel : ViewModel() {

    // Estado interno del usuario (mutable dentro del ViewModel)
    var userName by mutableStateOf("")
        private set

    var userEmail by mutableStateOf("")
        private set

    var userType by mutableStateOf("") // "cliente" o "tecnico"
        private set

    /**
     * Asigna los datos del usuario al iniciar sesión.
     */
    fun setUserData(name: String, email: String, type: String) {
        userName = name
        userEmail = email
        userType = type
    }

    /**
     * Limpia los datos de sesión (por ejemplo, al cerrar sesión).
     */
    fun clearSession() {
        userName = ""
        userEmail = ""
        userType = ""
    }
}