package com.prueba2.reparafacil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeClienteScreen(navController: NavController, nombre: String, email: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🏠 Bienvenido $nombre", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("📧 $email")
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("agenda/lavadora") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) { Text("🧺 Reparar Lavadora") }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { navController.navigate("agenda/aire") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) { Text("❄️ Reparar Aire Acondicionado") }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { navController.navigate("agenda/calefon") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) { Text("🔥 Reparar Calefón") }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { navController.navigate("garantia/cliente") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) { Text("🪪 Ver mis Garantías") }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { navController.navigate("perfil/$nombre/$email") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) { Text("👤 Mi Perfil") }
    }
}
