package com.prueba2.reparafacil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.viewmodel.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun loadWelcomeMessage() {
        viewModelScope.launch {
            try {
                // En el futuro podrías obtener datos reales del repositorio
                _uiState.value = HomeUiState(
                    welcomeMessage = "🏠 Bienvenido a ReparaFácil"
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    error = e.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }
}
