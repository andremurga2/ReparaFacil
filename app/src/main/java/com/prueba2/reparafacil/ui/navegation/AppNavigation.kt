package com.prueba2.reparafacil.ui.navegation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }
    }
}

@Composable
fun HomeScreen(navController: androidx.navigation.NavController) {
    Button(onClick = { navController.navigate("profile") }) {
        Text("Ver Perfil")
    }
}

@Composable
fun ProfileScreen(navController: androidx.navigation.NavController) {
    Column {
        Text("Pantalla de perfil")
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}