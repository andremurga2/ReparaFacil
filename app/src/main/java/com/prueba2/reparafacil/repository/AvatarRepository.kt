package com.prueba2.reparafacil.repository

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// 🔹 DataStore para almacenar la URI del avatar
private val Context.avatarDataStore by preferencesDataStore(name = "avatar_preferences")

class AvatarRepository(private val context: Context) {

    // 🔹 Clave con estilo de nombre recomendado (sin guiones bajos)
    private val avatarUriKey = stringPreferencesKey("avatar_uri")

    /**
     * 🔄 Observa los cambios del avatar en tiempo real.
     * Retorna un Flow<Uri?> que emite el valor actual y futuros cambios.
     */
    fun getAvatarUri(): Flow<Uri?> = context.avatarDataStore.data.map { prefs ->
        runCatching { prefs[avatarUriKey]?.toUri() }.getOrNull()
    }

    /**
     * 💾 Guarda una nueva URI de avatar en DataStore.
     * Si el valor es null, elimina el registro.
     */
    suspend fun saveAvatarUri(uri: Uri?) {
        context.avatarDataStore.edit { prefs ->
            if (uri != null) {
                prefs[avatarUriKey] = uri.toString()
            } else {
                prefs.remove(avatarUriKey)
            }
        }
    }

    /**
     * 🧹 Elimina la URI guardada (restablecer avatar por defecto).
     */
    suspend fun clearAvatarUri() = saveAvatarUri(null)

    /**
     * ⚡ Obtiene el avatar actual una sola vez (sin usar Flow).
     * Ideal para operaciones que no requieren observar cambios.
     */
    suspend fun getAvatarUriOnce(): Uri? {
        val prefs = context.avatarDataStore.data.first()
        return runCatching { prefs[avatarUriKey]?.toUri() }.getOrNull()
    }
}