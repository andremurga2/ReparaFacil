package com.prueba2.reparafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.prueba2.reparafacil.ui.navegation.AppNavigation
import com.prueba2.reparafacil.ui.theme.ReparaFacilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appDependencies = AppDependencies.getInstance(this)

        setContent {
            ReparaFacilTheme {
                AppNavigation(appDependencies)
            }
        }
    }
}