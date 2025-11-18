package com.prueba2.reparafacil.ui.screens

import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.prueba2.reparafacil.viewmodel.ProfileViewModel
import java.io.File

@Composable
fun PerfilScreen(
    navController: NavController,
    nombre: String,
    email: String,
    viewModel: ProfileViewModel = viewModel()
) {
    val context = LocalContext.current
    val imageUri by viewModel.avatarUri.collectAsState() // Avatar actual

    // Archivo temporal para la cámara
    var cameraImageUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.updateAvatar(it) }
    }

    // Launcher cámara
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            cameraImageUri?.let { viewModel.updateAvatar(it) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUri ?: "file:///android_asset/avatar_default.jpg"
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
            Button(onClick = { galleryLauncher.launch("image/*") }) {
                Text("Galería")
            }

            Button(onClick = {
                // Crear archivo temporal solo cuando se use la cámara
                val tempFile = File.createTempFile(
                    "JPEG_${System.currentTimeMillis()}_", ".jpg",
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                )
                cameraImageUri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    tempFile
                )
                cameraLauncher.launch(cameraImageUri!!)
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
    }

    // Cargar avatar guardado al iniciar
    LaunchedEffect(Unit) {
        viewModel.loadSavedAvatar()
    }
}
