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

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun loadWelcomeMessage(userId: Int = 1) {
        viewModelScope.launch {
            val result = repository.fetchUser(userId)
            _uiState.value = result.fold(
                onSuccess = { user ->
                    _uiState.value.copy(
                        welcomeMessage = "Bienvenido, ${user.name}"
                    )
                },
                onFailure = { e ->
                    _uiState.value.copy(
                        error = e.localizedMessage ?: "Error desconocido"
                    )
                }
            )
        }
    }
}