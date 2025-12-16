📱 ReparaFácil
📌 Nombre de la aplicación

ReparaFácil

Aplicación móvil orientada a la gestión de servicios técnicos, permitiendo a clientes solicitar servicios y a técnicos administrar trabajos, estados y agenda, utilizando una arquitectura cliente–servidor con API REST.

👥 Integrantes del proyecto

Benjamín Verdejo

Álvaro Rivera

⚙️ Funcionalidades principales
🔐 Autenticación

Registro de usuarios

Inicio de sesión con JWT

Persistencia de sesión mediante token

👤 Gestión de usuarios

Diferenciación por roles (CLIENTE / TÉCNICO)

Visualización de información básica del usuario autenticado

🛠️ Servicios

Listado de servicios disponibles

Solicitud de servicios técnicos

Visualización del estado del servicio

📅 Agenda

Visualización de servicios agendados

Asociación de servicios a técnicos

📱 App móvil

Navegación por pantallas según rol

Manejo de estados con ViewModel

Validaciones de formularios

Persistencia local de sesión

🌐 Endpoints utilizados
🔹 Endpoints propios (API ReparaFácil)

Autenticación

POST /api/auth/login

POST /api/auth/register

Usuarios

GET /api/users/me

Servicios

GET /api/services

POST /api/services

GET /api/services/{id}

Agenda

GET /api/agenda

POST /api/agenda

Todos los endpoints protegidos utilizan JWT (Bearer Token).

🔹 Endpoints externos

Este proyecto no utiliza APIs externas.
Toda la comunicación se realiza contra el microservicio propio desarrollado en Spring Boot.

▶️ Instrucciones para ejecutar el proyecto
🔧 Backend (Microservicio)

Requisitos

Java 17+

Maven

IDE (IntelliJ / Eclipse)

Base de datos (H2 o la configurada en application.yml)

Pasos

cd reparafacil-api-main
mvn clean install
mvn spring-boot:run


La API quedará disponible en:

http://localhost:8080

📱 App móvil (Android)

Requisitos

Android Studio

SDK Android 24+

Emulador o dispositivo físico

Pasos

Abrir Android Studio

Importar el proyecto ReparaFacil-main

Verificar URL base del backend en:

data/remote/api/ApiService.kt


Ejecutar la app

🔐 APK firmado

El proyecto cuenta con APK firmado

Archivo .jks ubicado en:

app/keystore/reparafacil-release.jks


APK generado en:

app/build/outputs/apk/release/app-release.apk


⚠️ Nota: El archivo .jks debe mantenerse de forma segura y no compartirse públicamente.

📂 Código fuente
📦 Microservicio (Backend)

Ubicación:

reparafacil-api-main/


Incluye:

Controladores REST

Seguridad con JWT

Servicios y repositorios

DTOs

Configuración Spring Security

📦 App móvil (Android)

Ubicación:

ReparaFacil-main/


Incluye:

Arquitectura MVVM

ViewModels

Repositorios

DataStore / SessionManager

Navegación con Compose

Consumo de API REST

🧱 Arquitectura utilizada

Cliente–Servidor

API REST

JWT para autenticación

MVVM en Android

Spring Boot + Security en Backend

✅ Estado del proyecto

✔ Autenticación funcional
✔ Flujo de pantallas corregido
✔ Comunicación App ↔ API estable
✔ Proyecto listo para evaluación
