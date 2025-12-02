package com.prueba2.reparafacil.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.AppDependencies
import com.prueba2.reparafacil.data.remote.dto.LoginRequest
import com.prueba2.reparafacil.viewmodel.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    // ✅ Obtiene el repositorio desde AppDependencies
    private val dependencies = AppDependencies.getInstance(application)
    private val repository = dependencies.userRepository

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    // 🟢 Actualiza el email en el estado
    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    // 🟢 Actualiza la contraseña en el estado
    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    // 🔐 Ejecuta el inicio de sesión
    fun login(onSuccess: () -> Unit) {
        val current = _uiState.value
        viewModelScope.launch {
            _uiState.value = current.copy(isLoading = true, error = null)
            try {
                repository.login(LoginRequest(current.email, current.password))
                _uiState.value = current.copy(isLoading = false)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = current.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }
}