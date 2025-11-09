package com.prueba2.reparafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.prueba2.reparafacil.navigation.AppNavigation
import com.prueba2.reparafacil.ui.theme.ReparaFacilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // 🎨 Tema general de la aplicación
            ReparaFacilTheme {
                // 🧱 Superficie base que usa el color del tema
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 🚀 Punto de entrada principal: navegación entre pantallas
                    AppNavigation()
                }
            }
        }
    }
}
