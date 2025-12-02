package com.prueba2.reparafacil

import android.content.Context
import com.prueba2.reparafacil.data.local.SessionManager
import com.prueba2.reparafacil.repository.AvatarRepository
import com.prueba2.reparafacil.repository.UserRepository

/**
 * Contenedor manual de dependencias para la aplicación.
 */
class AppDependencies private constructor(
    val userRepository: UserRepository,
    val avatarRepository: AvatarRepository,
    val sessionManager: SessionManager
) {
    companion object {
        @Volatile private var instance: AppDependencies? = null

        fun getInstance(context: Context): AppDependencies {
            return instance ?: synchronized(this) {
                instance ?: buildDependencies(context).also { instance = it }
            }
        }

        private fun buildDependencies(context: Context): AppDependencies {
            val sessionManager = SessionManager(context)
            val userRepository = UserRepository(context)
            val avatarRepository = AvatarRepository(context)

            return AppDependencies(
                userRepository = userRepository,
                avatarRepository = avatarRepository,
                sessionManager = sessionManager
            )
        }
    }
}