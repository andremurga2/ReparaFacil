# 📖 Guía de Uso - ReparaFácil API

## 🚀 Inicio Rápido

### URL Base
- **API:** http://localhost:3000/api
- **Swagger Docs:** http://localhost:3000/api/docs

### Usuario Admin Predeterminado
```json
{
  "email": "admin@sistema.com",
  "password": "Admin123456"
}
```

---

## 🔐 Autenticación

### 1. Registrar Usuario
**POST** `/api/auth/register`

**Roles disponibles:** `CLIENTE`, `TECNICO`

```json
{
  "email": "usuario@example.com",
  "password": "password123",
  "role": "CLIENTE",
  "nombre": "Juan Pérez",
  "telefono": "+51 987654321",
  "direccion": "Av. Principal 123"
}
```

**Respuesta exitosa:**
```json
{
  "success": true,
  "message": "Usuario registrado exitosamente",
  "data": {
    "user": {
      "_id": "507f1f77bcf86cd799439011",
      "email": "usuario@example.com",
      "role": "CLIENTE",
      "isActive": true,
      "emailVerified": false,
      "createdAt": "2024-01-01T00:00:00.000Z"
    },
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

### 2. Iniciar Sesión
**POST** `/api/auth/login`

```json
{
  "email": "usuario@example.com",
  "password": "password123"
}
```

**Respuesta exitosa:**
```json
{
  "success": true,
  "message": "Inicio de sesión exitoso",
  "data": {
    "user": {
      "_id": "507f1f77bcf86cd799439011",
      "email": "usuario@example.com",
      "role": "CLIENTE"
    },
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

### 3. Obtener Perfil Actual
**GET** `/api/auth/profile`

**Headers:**
```
Authorization: Bearer {access_token}
```

---

## 👤 Perfiles de Usuario

### ClienteProfile (Rol: CLIENTE)

#### Obtener mi perfil
**GET** `/api/cliente-profile/me`

**Headers:**
```
Authorization: Bearer {token}
```

**Respuesta:**
```json
{
  "_id": "507f1f77bcf86cd799439011",
  "user": "507f1f77bcf86cd799439012",
  "nombre": "Juan Pérez",
  "telefono": "+51 987654321",
  "direccion": "Av. Principal 123",
  "createdAt": "2024-01-01T00:00:00.000Z",
  "updatedAt": "2024-01-01T00:00:00.000Z"
}
```

#### Actualizar mi perfil
**PUT** `/api/cliente-profile/me`

```json
{
  "nombre": "Juan Carlos Pérez",
  "telefono": "+51 999888777",
  "direccion": "Nueva dirección 456"
}
```

#### Listar todos los perfiles (Solo Admin)
**GET** `/api/cliente-profile`

#### Obtener perfil por userId (Solo Admin)
**GET** `/api/cliente-profile/{userId}`

---

### TecnicoProfile (Rol: TECNICO)

#### Obtener mi perfil
**GET** `/api/tecnico-profile/me`

**Respuesta:**
```json
{
  "_id": "507f1f77bcf86cd799439011",
  "user": "507f1f77bcf86cd799439012",
  "nombreCompleto": "Carlos Técnico",
  "telefono": "+51 987654321",
  "especialidad": "Reparación de electrodomésticos",
  "createdAt": "2024-01-01T00:00:00.000Z",
  "updatedAt": "2024-01-01T00:00:00.000Z"
}
```

#### Actualizar mi perfil
**PUT** `/api/tecnico-profile/me`

```json
{
  "nombreCompleto": "Carlos Alberto Técnico",
  "telefono": "+51 999888777",
  "especialidad": "Electrodomésticos y electrónica"
}
```

---

## 📸 Sistema de Imágenes

### ⚠️ IMPORTANTE
- La imagen es **OPCIONAL** al crear servicios/reparaciones/técnicos/agenda/garantía
- Puedes crear el elemento **SIN imagen** y agregarla después
- Formatos soportados: **JPG, JPEG, PNG, GIF, WEBP**
- Tamaño máximo: **5 MB**
- Se genera un **thumbnail automático** (200x200px)

### 🎯 Flujo Recomendado

#### Paso 1: Crear Entidad (ej: Reparación)
**POST** `/api/reparacion`

```json
{
  "descripcion": "Reparación de laptop",
  "estado": "pendiente",
  "cliente": "507f1f77bcf86cd799439011"
}
```

**Respuesta:**
```json
{
  "success": true,
  "message": "Reparacion creado exitosamente",
  "data": {
    "_id": "674a1b2c3d4e5f6a7b8c9d0e",
    "descripcion": "Reparación de laptop",
    "imagen": null,
    "imagenThumbnail": null,
    ...
  }
}
```

**💡 Guarda el `_id`** para el siguiente paso.

#### Paso 2: Subir Imagen
**POST** `/api/reparacion/{id}/upload-image`

**Content-Type:** `multipart/form-data`

**Headers:**
```
Authorization: Bearer {token}
```

**Body:**
- **Key:** `file`
- **Type:** File
- **Value:** [Seleccionar imagen]

**Respuesta:**
```json
{
  "success": true,
  "message": "Imagen subida y asociada exitosamente",
  "data": {
    "reparacion": {
      "_id": "674a1b2c3d4e5f6a7b8c9d0e",
      "imagen": "uploads/1700000000000-imagen.jpg",
      "imagenThumbnail": "uploads/thumbnails/thumb-1700000000000-imagen.jpg"
    },
    "upload": {
      "url": "uploads/1700000000000-imagen.jpg",
      "thumbnailUrl": "uploads/thumbnails/thumb-1700000000000-imagen.jpg"
    }
  }
}
```

### 🖼️ Ver Imágenes
**Imagen original:**
```
http://localhost:3000/uploads/1700000000000-imagen.jpg
```

**Thumbnail:**
```
http://localhost:3000/uploads/thumbnails/thumb-1700000000000-imagen.jpg
```

---

## 📮 Uso con Postman

### 1. Importar Colección
Importa el archivo: `reparafacil-api.postman_collection.json`

### 2. Hacer Login
1. Ve a: **Auth** → **Login**
2. Ejecuta la petición
3. Copia el `access_token` de la respuesta

### 3. Configurar Token
En los headers de las peticiones protegidas:
```
Authorization: Bearer {access_token}
```

### 4. Subir Imagen
1. Crea la entidad primero (ej: reparación)
2. Copia el `_id` de la respuesta
3. Ve a: **Upload por Entidad** → **Upload Reparacion Image**
4. Reemplaza `{id}` en la URL con el ID copiado
5. En **Body**:
   - Selecciona tipo: `form-data`
   - Key: `file`
   - Type: `File`
   - Seleccionar imagen
6. Envía la petición

---

## 💻 Ejemplos con cURL

### Login y Crear Reparación con Imagen

```bash
# 1. Login
TOKEN=$(curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"cliente@example.com","password":"password123"}' \
  | jq -r '.data.access_token')

# 2. Crear Reparación
REPARACION_ID=$(curl -X POST http://localhost:3000/api/reparacion \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"descripcion":"Reparación de laptop","estado":"pendiente"}' \
  | jq -r '.data._id')

echo "Reparación creada con ID: $REPARACION_ID"

# 3. Subir imagen
curl -X POST http://localhost:3000/api/reparacion/$REPARACION_ID/upload-image \
  -H "Authorization: Bearer $TOKEN" \
  -F "file=@/ruta/a/tu/imagen.jpg"

echo "✅ Imagen subida exitosamente!"
```

---

## 🔧 Endpoints Disponibles

### Reparación
- **POST** `/api/reparacion` - Crear reparación
- **POST** `/api/reparacion/{id}/upload-image` - Subir imagen
- **GET** `/api/reparacion` - Listar todas
- **GET** `/api/reparacion/{id}` - Obtener por ID
- **PATCH** `/api/reparacion/{id}` - Actualizar
- **DELETE** `/api/reparacion/{id}` - Eliminar

### Técnico
- **POST** `/api/tecnico` - Crear técnico
- **POST** `/api/tecnico/{id}/upload-image` - Subir imagen
- **GET** `/api/tecnico` - Listar todos
- **GET** `/api/tecnico/{id}` - Obtener por ID
- **PATCH** `/api/tecnico/{id}` - Actualizar
- **DELETE** `/api/tecnico/{id}` - Eliminar

### Agenda
- **POST** `/api/agenda` - Crear cita
- **POST** `/api/agenda/{id}/upload-image` - Subir imagen
- **GET** `/api/agenda` - Listar todas
- **GET** `/api/agenda/{id}` - Obtener por ID
- **PATCH** `/api/agenda/{id}` - Actualizar
- **DELETE** `/api/agenda/{id}` - Eliminar

### Garantía
- **POST** `/api/garantia` - Crear garantía
- **POST** `/api/garantia/{id}/upload-image` - Subir imagen
- **GET** `/api/garantia` - Listar todas
- **GET** `/api/garantia/{id}` - Obtener por ID
- **PATCH** `/api/garantia/{id}` - Actualizar
- **DELETE** `/api/garantia/{id}` - Eliminar

---

## ⚡ Rate Limiting

El API tiene límites de velocidad:
- **short:** 3 requests por segundo
- **medium:** 20 requests por 10 segundos
- **long:** 100 requests por minuto

Si excedes el límite: **429 Too Many Requests**

---

## ❌ Errores Comunes

### "No se proporcionó ningún archivo"
**Causa:** No se envió el archivo o el campo no se llama `file`

**Solución:**
- En Postman: Key debe ser exactamente `file`
- En cURL: Usa `-F "file=@/ruta/imagen.jpg"`

### "El archivo debe ser una imagen"
**Causa:** Archivo no válido

**Solución:**
- Verifica que sea JPG, PNG, GIF o WEBP
- Verifica que no esté corrupto

### "401 Unauthorized"
**Causa:** Token inválido o expirado

**Solución:**
- Haz login nuevamente
- Copia el nuevo `access_token`

### "404 Not Found" al ver imagen
**Causa:** Ruta incorrecta

**Solución:**
- Verifica que el servidor esté corriendo
- Usa la URL exacta devuelta por el endpoint
- Debe empezar con `http://localhost:3000/uploads/`

---

## 🔑 Roles y Permisos

### Usuario autenticado
- Acceso a sus propios datos (endpoints `/me`)
- Crear y gestionar sus propias entidades

### ADMIN
- Acceso completo al sistema
- Ver todos los usuarios y perfiles
- Gestionar todas las entidades

---

## 📦 Estructura de Respuestas

### Respuesta Exitosa
```json
{
  "success": true,
  "message": "Operación exitosa",
  "data": { ... }
}
```

### Respuesta con Lista
```json
{
  "success": true,
  "data": [ ... ],
  "total": 10
}
```

### Respuesta de Error
```json
{
  "statusCode": 400,
  "message": "Descripción del error",
  "error": "Bad Request"
}
```

---

## 🛠️ Solución de Problemas

### MongoDB no conecta
Verifica que `MONGO_URI` esté en `.env` con el formato:
```
MONGO_URI=mongodb+srv://usuario:contraseña@cluster.mongodb.net/nombredb?retryWrites=true&w=majority
```

### Puerto en uso
Cambia el puerto en `.env`:
```
PORT=3001
```

### Token JWT inválido
Genera uno nuevo haciendo login con credenciales válidas.

---

## 📞 Soporte

Para más información, consulta:
- **Swagger UI:** http://localhost:3000/api/docs
- **Colección Postman:** `reparafacil-api.postman_collection.json`
