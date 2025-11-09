package com.prueba2.reparafacil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GarantiaScreen(navController: NavController, tipoUsuario: String) {
    val clienteGarantias = listOf(
        "Lavadora LG - Garantía activa hasta 2025",
        "Aire acondicionado Samsung - Garantía vencida",
        "Calefón Bosch - Garantía activa hasta 2026"
    )
    val tecnicoGarantias = listOf(
        "Reparación Lavadora - Garantía hasta 2025",
        "Reparación Calefón - Garantía hasta 2024",
        "Reparación Aire acondicionado - Garantía hasta 2025"
    )

    val listaGarantias = if (tipoUsuario.lowercase() == "tecnico") tecnicoGarantias else clienteGarantias

    Scaffold(
        topBar = { TopAppBar(title = { Text("🪪 Garantías") }) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* acción agregar garantía */ },
                containerColor = MaterialTheme.colorScheme.primary,
                content = { Text("➕ Agregar") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listaGarantias) { garantia ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(garantia, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
