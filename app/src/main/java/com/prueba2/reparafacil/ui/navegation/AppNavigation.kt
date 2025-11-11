package com.prueba2.reparafacil.ui.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prueba2.reparafacil.AppDependencies
import com.prueba2.reparafacil.ui.screens.*

@Composable
fun AppNavigation(appDependencies: AppDependencies) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
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

        composable(
            route = "homeCliente/{nombre}/{email}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            HomeClienteScreen(navController, nombre, email, appDependencies)
        }

        composable(
            route = "homeTecnico/{nombre}/{email}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            HomeTecnicoScreen(navController, nombre, email, appDependencies)
        }

        composable(
            route = "perfil/{nombre}/{email}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            PerfilScreen(navController, nombre, email) // ✅ Solo 3 parámetros
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }
    }
}