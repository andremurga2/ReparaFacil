ReparaFacil

1. Caso elegido y alcance

Caso: ReparaFacil – Aplicación de servicios técnicos a domicilio.
Alcance EP3: Diseño de UI, validaciones, navegación, gestión de estado, persistencia local y uso de recursos nativos (cámara y galería).

Principales puntos del proyecto:
1. Login y Registro de usuarios con navegación según tipo (Cliente/Técnico).
2. Home Cliente y Home Técnico con paso de datos (nombre y email).
3. Pantalla de Perfil con avatar editable (cámara o galería) y persistencia local.

Otras características:
- Gestión de estado con ViewModel y StateFlow.
- Persistencia de avatar usando DataStore.
- Implementacion de avatar por defecto cuando no hay imagen cargada.

2. Requisitos y ejecución

Stack: Kotlin, Jetpack Compose, Navigation Compose, Coil, DataStore, Android SDK 24+.

Instalación:

Abrir en Android Studio y sincronizar Gradle.

Ejecución:
git clone https://github.com/andremurga2/ReparaFacil.git
Ejecutar en un emulador o dispositivo Android 16+, preferentemente con cámara habilitada.

3. Arquitectura y flujo

Estructura de carpetas:
- ui.screens → Pantallas Compose (Login, Registro, Home, Perfil, Agenda, Garantía).
- ui.navigation → Gestión de rutas y argumentos entre pantallas.
- ui.components → Aca se guarda el ImagePickerDialog
- ui.navegation → Se almacena todo lo que es la navegacion de la aplicacion a traves de AppNavigation.kt
- viewmodel → Estado y lógica de UI.
- viewmodel.state → Define los estados de cada pantalla (carga, éxito, error) y mantiene los datos reactivos.
- repository → Acceso a datos y persistencia local, además de la lógica de negocio simple para obtener/guardar información.
- data → DTOs, recursos y utilidades generales.
- data.local → Maneja persistencia local, como SessionManager y almacenamiento de avatar con DataStore.
- data.remote → Comunicación con APIs externas, DTOs de request/response y servicios como ApiService.

Gestión de estado: ViewModel + StateFlow para mostrar avatar, mensajes de error y cargar datos.
Navegación: Stack navigation con argumentos (nombre/email), botón de backstack para cerrar pantallas y navegación condicional según tipo de usuario.

4. Funcionalidades

- Formulario validado para login y registro.
- Navegación entre pantallas con backstack.
- Uso de recursos nativos: cámara y galería con permisos y fallback.
- Avatar por defecto si no hay imagen disponible.
- Botones de acción en perfil para actualizar avatar.

6. User flows

Flujo principal:
1. Usuario inicia sesión → redirigido a HomeCliente o HomeTecnico.
2. Desde Home → acceso a Perfil, Agenda o Garantía.
3. Perfil → usuario puede actualizar avatar desde galería o cámara, con persistencia local.
4. Botón "Cerrar" → vuelve a Login.

Casos de error:
- Imagen de avatar no disponible → se muestra avatar por defecto.
- Error en carga de avatar → se notifica en UI.
