package com.prueba2.reparafacil.data.remote

import com.prueba2.reparafacil.data.remote.dto.*
import retrofit2.http.*

/**
 * Interfaz principal de la API remota.
 * Define los endpoints del backend (ejemplo basado en DummyJSON o API similar).
 */
interface ApiService {

    /**
     * 🔐 LOGIN - Autenticar usuario
     * POST /user/login
     *
     * Ejemplo:
     * val response = apiService.login(LoginRequest("emilys", "emilyspass"))
     * sessionManager.saveAuthToken(response.accessToken)
     */
    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    /**
     * 👤 OBTENER USUARIO ACTUAL (requiere autenticación JWT)
     * GET /user/me
     *
     * El token JWT se añade automáticamente por el AuthInterceptor.
     */
    @GET("user/me")
    suspend fun getCurrentUser(): UserDto

    /**
     * 📋 OBTENER LISTA DE USUARIOS
     * GET /user
     *
     * Ejemplo:
     * val response = apiService.getUsers()
     * val usersList = response.users
     */
    @GET("user")
    suspend fun getUsers(): UsersResponse

    /**
     * 🔍 BUSCAR USUARIOS POR NOMBRE
     * GET /user/search?q={query}
     */
    @GET("user/search")
    suspend fun searchUsers(@Query("q") query: String): UsersResponse

    /**
     * 👤 OBTENER USUARIO POR ID
     * GET /user/{id}
     */
    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDto

    /**
     * 📝 REGISTRO DE NUEVO USUARIO
     * POST /user/register
     */
    @POST("user/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}
