package com.prueba2.reparafacil.data.remote.dto
import com.google.gson.annotations.SerializedName

/**
 * DTO para la solicitud de registro.
 * Datos que enviamos al servidor.
 */
data class RegisterRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)

/**
 * DTO para la respuesta de registro.
 * Datos que recibimos del servidor tras un registro exitoso.
 */
data class RegisterResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("token")
    val token: String? = null // Token JWT opcional tras el registro
)