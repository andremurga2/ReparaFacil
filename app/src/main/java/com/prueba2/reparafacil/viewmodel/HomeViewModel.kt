package com.prueba2.reparafacil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val welcomeMessage: String = "",
    val error: String? = null
)

class HomeViewModel : ViewModel() {

    // ⚠️ En esta versión simple no necesitamos el repositorio todavía
    // Solo mostramos un mensaje fijo.
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun loadWelcomeMessage() {
        viewModelScope.launch {
            try {
                // 🟢 Aquí podrías traer datos reales desde el repositorio en el futuro.
                _uiState.value = HomeUiState(welcomeMessage = "🏠 Bienvenido a ReparaFácil")
            } catch (e: Exception) {
                _uiState.value = HomeUiState(error = e.localizedMessage ?: "Error desconocido")
            }
        }
    }
}
