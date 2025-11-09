package com.prueba2.reparafacil.data.remote.dto

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val id: Int,
    val name: String,
    val email: String,
    val token: String? = null
)
