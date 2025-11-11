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
fun HomeTecnicoScreen(
    navController: NavController,
    nombre: String,
    email: String,
    appDependencies: AppDependencies
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Panel Técnico - $nombre") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { navController.navigate("agenda/mis_servicios") }) {
                Text("Ver Agenda de Servicios")
            }

            Button(onClick = { navController.navigate("garantia/tecnico") }) {
                Text("Revisar Garantías")
            }

            Button(onClick = { navController.navigate("perfil/$nombre/$email") }) {
                Text("Mi Perfil")
            }
        }
    }
}