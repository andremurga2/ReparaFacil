package com.prueba2.reparafacil.repository

import android.content.Context
import com.prueba2.reparafacil.data.remote.ApiService
import com.prueba2.reparafacil.data.remote.RetrofitClient
import com.prueba2.reparafacil.data.remote.dto.*

class UserRepository(context: Context) {

    private val api: ApiService = RetrofitClient.create(context).create(ApiService::class.java)

    suspend fun login(request: LoginRequest): LoginResponse {
        return api.login(request)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return api.register(request)
    }

    suspend fun fetchUser(id: Int): Result<UserDto> {
        return try {
            val user = api.getUserById(id)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
