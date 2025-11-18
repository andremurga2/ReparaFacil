package com.prueba2.reparafacil.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    // URI del avatar (galería o cámara)
    private val _avatarUri = MutableStateFlow<Uri?>(null)
    val avatarUri: StateFlow<Uri?> = _avatarUri

    // Mensaje de error opcional
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Actualizar avatar desde galería
    fun updateAvatar(uri: Uri?) {
        if (uri != null) {
            _avatarUri.value = uri
            _errorMessage.value = null
        } else {
            _errorMessage.value = "No se pudo seleccionar la imagen."
        }
    }

    // Cargar avatar guardado al iniciar (opcional)
    fun loadSavedAvatar() {
        // Aquí podrías cargar la URI guardada de DataStore o SharedPreferences
        // Por ahora, nada: se usará el avatar por defecto de assets
    }
}