package com.prueba2.reparafacil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AgendaScreen(servicioSeleccionado: String = "general") {

    val todasLasCitas = mapOf(
        "lavadora" to listOf(
            Cita("Reparación Lavadora", "Lunes 10 Nov - 10:00 AM", "Técnico: Juan Pérez"),
            Cita("Revisión Lavadora Samsung", "Martes 11 Nov - 16:00 PM", "Técnico: Ana Torres")
        ),
        "aire" to listOf(
            Cita("Instalación Aire Acondicionado", "Miércoles 12 Nov - 09:30 AM", "Técnico: Carlos Díaz"),
            Cita("Mantenimiento Aire LG", "Viernes 14 Nov - 14:00 PM", "Técnico: Marta Silva")
        ),
        "calefon" to listOf(
            Cita("Revisión de Calefón", "Martes 18 Nov - 11:15 AM", "Técnico: Marta Silva"),
            Cita("Cambio de Termostato", "Jueves 20 Nov - 15:45 PM", "Técnico: Pedro Gómez")
        ),
        "general" to listOf(
            Cita("Diagnóstico General", "Lunes 10 Nov - 10:00 AM", "Técnico: Equipo ReparaFácil")
        )
    )

    val citasMostradas = todasLasCitas[servicioSeleccionado] ?: todasLasCitas["general"]!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🗓️ Agenda - ${servicioSeleccionado.capitalize()}",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(citasMostradas) { cita ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("📌 ${cita.servicio}", style = MaterialTheme.typography.bodyLarge)
                        Text("⏰ ${cita.fecha}", style = MaterialTheme.typography.bodyMedium)
                        Text("👷 ${cita.tecnico}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

data class Cita(
    val servicio: String,
    val fecha: String,
    val tecnico: String
)
