package com.prueba2.reparafacil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.repository.ScheduleRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(servicioSeleccionado: String) {
    val context = LocalContext.current
    val repo = remember { ScheduleRepository(context) }
    val scope = rememberCoroutineScope()
    val schedules by repo.getAllSchedules().collectAsState(initial = emptyList())

    Scaffold(topBar = { TopAppBar(title = { Text("Agenda - $servicioSeleccionado") }) }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(schedules) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp)) {
                            Text(item.servicio, style = MaterialTheme.typography.titleMedium)
                            Text("Fecha: ${item.fecha}")
                            Text("Dirección: ${item.direccion}")
                            Text("Contacto: ${item.contacto}")
                        }
                    }
                }
            }
        }
    }
}
