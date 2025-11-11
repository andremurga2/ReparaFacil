package com.prueba2.reparafacil.data.remote


import android.content.Context
import com.prueba2.reparafacil.data.local.SessionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // ⚙️ Base URL del backend ReparaFácil (cámbiala si usas otro endpoint)
    private const val BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:Rfm_61dW/"

    /**
     * Crea e inicializa Retrofit.
     * Se llama una vez (por ejemplo, desde Application o Repository)
     */
    fun create(context: Context): Retrofit {
        // 1️⃣ Administrador de sesión (maneja token)
        val sessionManager = SessionManager(context)

        // 2️⃣ Interceptor para añadir automáticamente el token
        val authInterceptor = AuthInterceptor(sessionManager)

        // 3️⃣ Interceptor para mostrar peticiones/respuestas (debugging)
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Cambiar a NONE en producción
        }

        // 4️⃣ Cliente HTTP configurado
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)     // Añade el token si existe
            .addInterceptor(loggingInterceptor)  // Log completo de la petición/respuesta
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

        // 5️⃣ Instancia Retrofit con convertidor JSON (Gson)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}