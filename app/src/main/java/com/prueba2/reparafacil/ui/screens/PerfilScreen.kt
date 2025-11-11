package com.prueba2.reparafacil.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.prueba2.reparafacil.R
import com.prueba2.reparafacil.viewmodel.ProfileViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PerfilScreen(
    navController: NavController,
    nombre: String,
    email: String
) {
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel(
        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory(
            context.applicationContext as android.app.Application
        )
    )

    val uiState by viewModel.uiState.collectAsState()

    var cameraUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher para seleccionar imagen desde galería
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.updateAvatar(it) }
    }

    // Launcher para tomar foto con cámara
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) cameraUri?.let { viewModel.updateAvatar(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Avatar
        Image(
            painter = rememberAsyncImagePainter(
                model = uiState.avatarUri ?: R.drawable.avatar_default
            ),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { pickImageLauncher.launch("image/*") }) {
                Text("Galería")
            }

            Button(onClick = {
                // Crear archivo temporal justo antes de lanzar la cámara
                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
                val storageDir = context.getExternalFilesDir("Pictures")
                val tempFile = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
                cameraUri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    tempFile
                )
                cameraUri?.let { takePictureLauncher.launch(it) }
            }) {
                Text("Cámara")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nombre: $nombre", style = MaterialTheme.typography.titleMedium)
        Text(text = "Email: $email", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Cerrar")
        }

        uiState.error?.let { errorMsg ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    // Cargar avatar guardado al iniciar
    LaunchedEffect(Unit) {
        viewModel.loadSavedAvatar()
    }
}
