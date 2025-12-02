package com.prueba2.reparafacil.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.AppDependencies
import com.prueba2.reparafacil.data.remote.dto.RegisterRequest
import com.prueba2.reparafacil.viewmodel.state.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    // 🔹 Obtener repositorio desde el contenedor
    private val dependencies = AppDependencies.getInstance(application)
    private val repository = dependencies.userRepository

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    // Actualiza el nombre del usuario
    fun onNameChange(newValue: String) {
        _uiState.update { it.copy(name = newValue) }
    }

    // Actualiza el correo
    fun onEmailChange(newValue: String) {
        _uiState.update { it.copy(email = newValue) }
    }

    // Actualiza la contraseña
    fun onPasswordChange(newValue: String) {
        _uiState.update { it.copy(password = newValue) }
    }

    // Ejecuta el registro
    fun register() {
        val current = _uiState.value

        // Validación simple
        if (current.name.isBlank() || current.email.isBlank() || current.password.isBlank()) {
            _uiState.update { it.copy(error = "Todos los campos son obligatorios") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                repository.register(RegisterRequest(current.name, current.email, current.password))
                _uiState.update { it.copy(isLoading = false, success = true) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Error desconocido"
                    )
                }
            }
        }
    }
}