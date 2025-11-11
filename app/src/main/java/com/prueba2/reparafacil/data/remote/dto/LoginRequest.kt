package com.prueba2.reparafacil.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para la solicitud de inicio de sesión.
 * Representa los datos que se envían al servidor en el login.
 */
data class LoginRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("expiresInMins")
    val expiresInMins: Int = 30 // Duración del token en minutos
)