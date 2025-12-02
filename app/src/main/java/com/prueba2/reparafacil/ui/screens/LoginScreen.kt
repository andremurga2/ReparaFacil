package com.prueba2.reparafacil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onClienteLogin: (String, String) -> Unit,
    onTecnicoLogin: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var tipoUsuario by remember { mutableStateOf("cliente") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("ReparaFácil - Iniciar Sesión") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text("Tipo de usuario:")
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FilterChip(selected = tipoUsuario == "cliente", onClick = { tipoUsuario = "cliente" }, label = { Text("Cliente") })
                FilterChip(selected = tipoUsuario == "tecnico", onClick = { tipoUsuario = "tecnico" }, label = { Text("Técnico") })
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (tipoUsuario == "cliente") onClienteLogin("Cliente", email)
                else onTecnicoLogin("Técnico", email)
            }) {
                Text("Ingresar")
            }

            Spacer(modifier = Modifier.height(12.dp))
            TextButton(onClick = onRegisterClick) { Text("Crear cuenta nueva") }
        }
    }
}