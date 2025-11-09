package com.prueba2.reparafacil.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prueba2.reparafacil.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        // 🔐 LOGIN
        composable("login") {
            LoginScreen(
                onClienteLogin = { nombre, email ->
                    navController.navigate("homeCliente/$nombre/$email") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onTecnicoLogin = { nombre, email ->
                    navController.navigate("homeTecnico/$nombre/$email") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        // 🏠 Cliente
        composable(
            "homeCliente/{nombre}/{email}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            HomeClienteScreen(navController, nombre, email)
        }

        // 🧰 Técnico
        composable(
            "homeTecnico/{nombre}/{email}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            HomeTecnicoScreen(navController, nombre, email)
        }

        // 📋 Agenda (ruta universal con parámetro)
        composable(
            "agenda/{servicio}",
            arguments = listOf(navArgument("servicio") { type = NavType.StringType })
        ) { backStackEntry ->
            val servicio = backStackEntry.arguments?.getString("servicio") ?: ""
            AgendaScreen(servicioSeleccionado = servicio)
        }

        // 🪪 Garantías
        composable(
            "garantia/{tipoUsuario}",
            arguments = listOf(navArgument("tipoUsuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val tipoUsuario = backStackEntry.arguments?.getString("tipoUsuario") ?: "cliente"
            GarantiaScreen(navController, tipoUsuario)
        }

        // 👤 Perfil
        composable(
            "perfil/{nombre}/{email}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            PerfilScreen(navController, nombre, email)
        }

        // 📝 Registro
        composable("register") {
            RegisterScreen(onRegisterSuccess = {
                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
            })
        }
    }
}
