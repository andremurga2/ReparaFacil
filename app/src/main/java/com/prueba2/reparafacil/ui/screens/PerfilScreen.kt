package com.prueba2.reparafacil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(navController: NavController, nombre: String, email: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("👤 Mi Perfil") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Nombre:", fontWeight = FontWeight.Bold)
            Text(nombre)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Correo:", fontWeight = FontWeight.Bold)
            Text(email)

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) { Text("🚪 Cerrar Sesión") }
        }
    }
}
