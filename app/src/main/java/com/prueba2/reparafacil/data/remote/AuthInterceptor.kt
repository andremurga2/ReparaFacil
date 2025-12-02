package com.prueba2.reparafacil.data.remote

import com.prueba2.reparafacil.data.local.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor que agrega automáticamente el token JWT a cada solicitud HTTP.
 *
 * - Recupera el token desde SessionManager (DataStore)
 * - Si existe, añade el encabezado "Authorization: Bearer <token>"
 * - Si no existe, deja pasar la petición original
 */
class AuthInterceptor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Recuperar token JWT desde DataStore
        val token = runBlocking {
            sessionManager.getAuthToken()
        }

        // Si no hay token, continuar sin modificar la solicitud
        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }

        // Crear una nueva solicitud con el encabezado de autorización
        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        // Continuar con la solicitud autenticada
        return chain.proceed(authenticatedRequest)
    }
}