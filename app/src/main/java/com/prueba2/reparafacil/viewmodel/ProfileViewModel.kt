package com.prueba2.reparafacil.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.AppDependencies
import com.prueba2.reparafacil.viewmodel.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val dependencies = AppDependencies.getInstance(application)
    private val userRepository = dependencies.userRepository
    private val avatarRepository = dependencies.avatarRepository

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadSavedAvatar()
    }

    /** Carga avatar guardado en DataStore al iniciar */
    fun loadSavedAvatar() {
        viewModelScope.launch {
            avatarRepository.getAvatarUri().collect { uri ->
                _uiState.update { it.copy(avatarUri = uri) }
            }
        }
    }

    /** Carga los datos de un usuario */
    fun loadUser(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = runCatching { userRepository.fetchUser(id).getOrThrow() }

            result.fold(
                onSuccess = { user ->
                    _uiState.update {
                        it.copy(
                            userName = user.name,
                            userEmail = user.email ?: "Sin email",
                            avatarUri = user.profilePicture?.let { Uri.parse(it) },
                            isLoading = false,
                            error = null
                        )
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = e.localizedMessage ?: "Error al cargar usuario"
                        )
                    }
                }
            )
        }
    }

    /** Actualiza el avatar y lo guarda en DataStore */
    fun updateAvatar(uri: Uri?) {
        viewModelScope.launch {
            avatarRepository.saveAvatarUri(uri)
            // Flow se encargará de actualizar automáticamente el estado
        }
    }

    /** Limpia cualquier error */
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}