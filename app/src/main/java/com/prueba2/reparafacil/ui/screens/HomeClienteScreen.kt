package com.prueba2.reparafacil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prueba2.reparafacil.AppDependencies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeClienteScreen(
    navController: NavController,
    nombre: String,
    email: String,
    appDependencies: AppDependencies
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Bienvenido, $nombre") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Selecciona una acción:", style = MaterialTheme.typography.titleMedium)

            Button(onClick = { navController.navigate("agenda/servicio_basico") }) {
                Text("Agendar Servicio")
            }

            Button(onClick = { navController.navigate("garantia/cliente") }) {
                Text("Ver Garantías")
            }

            Button(onClick = { navController.navigate("perfil/$nombre/$email") }) {
                Text("Mi Perfil")
            }
        }
    }
}
