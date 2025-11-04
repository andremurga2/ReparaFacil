package com.prueba2.reparafacil.repository

import android.content.Context
import com.prueba2.reparafacil.data.remote.ApiService
import com.prueba2.reparafacil.data.remote.RetrofitClient
import com.prueba2.reparafacil.data.remote.dto.UserDto
import com.prueba2.reparafacil.data.remote.dto.LoginRequest
import com.prueba2.reparafacil.data.remote.dto.LoginResponse

class UserRepository(context: Context) {

    // Crear la instancia del API Service
    private val apiService: ApiService = RetrofitClient
        .create(context)
        .create(ApiService::class.java)

    // Función para login
    suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = apiService.login(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Función para obtener usuario por ID
    suspend fun fetchUser(id: Int = 1): Result<UserDto> {
        return try {
            val user = apiService.getUserById(id)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
