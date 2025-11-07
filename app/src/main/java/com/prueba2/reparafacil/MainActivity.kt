package com.prueba2.reparafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.prueba2.reparafacil.ui.navegation.AppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 👇 usamos el padding dentro de un Box
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation() // 🚀 tu navegación, sin cambios
                    }
                }
            }
        }
    }
}