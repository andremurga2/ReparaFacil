package com.prueba2.reparafacil.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO (Data Transfer Object)
 * Representa los datos del usuario que viajan entre la app y el servidor.
 */
data class UserDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("profilePicture")
    val profilePicture: String? = null
)

