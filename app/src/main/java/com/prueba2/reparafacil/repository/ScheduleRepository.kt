package com.prueba2.reparafacil.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

// 🔹 DataStore para persistir agendamientos
private val Context.scheduleDataStore by preferencesDataStore(name = "schedule_prefs")

class ScheduleRepository(private val context: Context) {

    // 🔹 Clave con naming correcto
    private val schedulesKey = stringPreferencesKey("schedules_json")

    /**
     * 🔄 Retorna todos los agendamientos guardados como Flow reactivo.
     * Cada vez que se modifica el DataStore, el flujo emite la nueva lista.
     */
    fun getAllSchedules(): Flow<List<ScheduledItem>> {
        return context.scheduleDataStore.data.map { prefs ->
            val json = prefs[schedulesKey] ?: "[]"
            runCatching {
                val arr = JSONArray(json)
                List(arr.length()) { i ->
                    ScheduledItem.fromJson(arr.getJSONObject(i))
                }
            }.getOrDefault(emptyList())
        }
    }

    /**
     * 💾 Guarda un nuevo agendamiento.
     * Si ya existe un JSON, lo lee, agrega el nuevo y lo vuelve a guardar.
     */
    suspend fun saveSchedule(item: ScheduledItem) {
        context.scheduleDataStore.edit { prefs ->
            val json = prefs[schedulesKey] ?: "[]"
            val arr = JSONArray(json)
            arr.put(item.toJson())
            prefs[schedulesKey] = arr.toString()
        }
    }

    /**
     * ❌ Elimina un agendamiento por ID.
     * Reconstruye el arreglo JSON excluyendo el ID especificado.
     */
    suspend fun deleteSchedule(id: String) {
        context.scheduleDataStore.edit { prefs ->
            val json = prefs[schedulesKey] ?: "[]"
            val arr = JSONArray(json)
            val newArr = JSONArray()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                if (obj.optString("id") != id) {
                    newArr.put(obj)
                }
            }
            prefs[schedulesKey] = newArr.toString()
        }
    }
}

/**
 * 🧱 Representa un ítem agendado con utilidades JSON.
 */
data class ScheduledItem(
    val id: String,
    val servicio: String,
    val fecha: String,
    val direccion: String,
    val contacto: String
) {
    fun toJson(): JSONObject = JSONObject().apply {
        put("id", id)
        put("servicio", servicio)
        put("fecha", fecha)
        put("direccion", direccion)
        put("contacto", contacto)
    }

    companion object {
        fun fromJson(obj: JSONObject): ScheduledItem = ScheduledItem(
            id = obj.optString("id"),
            servicio = obj.optString("servicio"),
            fecha = obj.optString("fecha"),
            direccion = obj.optString("direccion"),
            contacto = obj.optString("contacto")
        )
    }
}