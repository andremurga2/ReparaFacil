package com.prueba2.reparafacil.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para la respuesta de inicio de sesión.
 * Representa los datos que RECIBIMOS del servidor tras un login exitoso.
 */
data class LoginResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("accessToken")
    val accessToken: String, // 🔑 Token JWT - se guarda en SessionManager

    @SerializedName("refreshToken")
    val refreshToken: String? = null // Opcional: para renovar el token
)