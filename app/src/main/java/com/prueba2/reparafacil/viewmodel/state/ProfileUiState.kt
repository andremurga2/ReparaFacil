package com.prueba2.reparafacil.viewmodel.state

import android.net.Uri

/**
 * Estado de la pantalla de perfil
 */
data class ProfileUiState(
    val isLoading: Boolean = false,          // Indica si está cargando datos
    val userName: String = "",               // Nombre del usuario
    val userEmail: String = "",              // Correo del usuario
    val avatarUri: Uri? = null,              // URI de la foto de perfil
    val error: String? = null                // Mensaje de error si ocurre algo
)