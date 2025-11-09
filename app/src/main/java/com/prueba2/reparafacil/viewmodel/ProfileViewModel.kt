package com.prueba2.reparafacil.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val userName: String = "",
    val userEmail: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun loadUser(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = repository.fetchUser(id)
            _uiState.value = result.fold(
                onSuccess = { user ->
                    ProfileUiState(
                        userName = user.name,
                        userEmail = user.email ?: "Sin email",
                        isLoading = false
                    )
                },
                onFailure = { e ->
                    _uiState.value.copy(isLoading = false, error = e.localizedMessage)
                }
            )
        }
    }
}
