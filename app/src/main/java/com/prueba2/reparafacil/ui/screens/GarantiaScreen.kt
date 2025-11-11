package com.prueba2.reparafacil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GarantiaScreen(navController: NavController, tipoUsuario: String) {
    Scaffold(topBar = { TopAppBar(title = { Text("Garantías - $tipoUsuario") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = if (tipoUsuario == "cliente")
                    "Aquí puedes revisar tus servicios con garantía activa."
                else
                    "Aquí puedes gestionar las solicitudes de garantía.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}