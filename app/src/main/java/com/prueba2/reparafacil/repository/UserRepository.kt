package com.prueba2.reparafacil.repository

import android.content.Context
import com.prueba2.reparafacil.data.local.SessionManager
import com.prueba2.reparafacil.data.remote.ApiService
import com.prueba2.reparafacil.data.remote.RetrofitClient
import com.prueba2.reparafacil.data.remote.dto.LoginRequest
import com.prueba2.reparafacil.data.remote.dto.LoginResponse
import com.prueba2.reparafacil.data.remote.dto.RegisterRequest
import com.prueba2.reparafacil.data.remote.dto.RegisterResponse
import com.prueba2.reparafacil.data.remote.dto.UserDto

/**
 * 📦 UserRepository
 * Maneja las operaciones relacionadas con el usuario:
 * autenticación, registro y obtención de información del perfil.
 */
class UserRepository(context: Context) {

    // 🔹 SessionManager para manejar tokens o sesión
    private val sessionManager = SessionManager(context)

    // 🔹 Servicio API configurado con Retrofit y SessionManager
    private val api: ApiService = RetrofitClient
        .create(context)
        .create(ApiService::class.java)

    /**
     * 🔐 Inicia sesión en el servidor y devuelve la respuesta con el token.
     */
    suspend fun login(request: LoginRequest): Result<LoginResponse> = runCatching {
        api.login(request)
    }

    /**
     * 📝 Registra un nuevo usuario en el sistema.
     */
    suspend fun register(request: RegisterRequest): Result<RegisterResponse> = runCatching {
        api.register(request)
    }

    /**
     * 👤 Obtiene la información de un usuario por su ID.
     */
    suspend fun fetchUser(id: Int): Result<UserDto> = runCatching {
        api.getUserById(id)
    }
}