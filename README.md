# 🏗️ Plataforma de Renta de Herramientas y Equipos de Construcción

Backend robusto para la gestión de alquiler de maquinaria y herramientas, desarrollado con **Spring Boot 3.3.0** y **Java 21**. Este proyecto implementa una **Arquitectura Hexagonal (Ports & Adapters)** estricta y **Domain-Driven Design (DDD)** para garantizar la escalabilidad, mantenibilidad y un bajo acoplamiento con tecnologías externas.

El sistema conecta a **Proveedores** (dueños de equipos) con **Clientes** (contratistas o particulares), gestionando todo el ciclo de vida del alquiler: desde la publicación del inventario y solicitudes de renta, hasta la facturación y pagos.

## 🚀 Características Principales

### Funcionalidades del Negocio
- ✅ **Gestión de Inventario**: Los proveedores pueden registrar, actualizar y gestionar su catálogo de herramientas.
- ✅ **Sistema de Reservas**: Los clientes pueden buscar herramientas, consultar disponibilidad y realizar alquileres.
- ✅ **Gestión de Pagos**: Integración para procesar pagos en línea y generar facturas.
- ✅ **Paneles de Control por Rol**: Vistas personalizadas para Administradores, Proveedores y Clientes.
- ✅ **Seguimiento de Alquileres**: Historial de alquileres, gestión de devoluciones y estado de los equipos.

### Características Técnicas
- ✅ **Autenticación Segura**: Sistema basado en **JWT** con Access Token y Refresh Token.
- ✅ **Autorización por Roles**: Tres niveles de acceso: `ADMIN`, `PROVEEDOR`, y `CLIENTE`.
- ✅ **Arquitectura Hexagonal Pura**: El dominio es agnóstico al framework, bases de datos y otros detalles de infraestructura.
- ✅ **Manejo Global de Excepciones**: Respuestas de error consistentes y claras.
- ✅ **Validación de Datos**: Uso de Jakarta Validation para asegurar la integridad de los datos de entrada.
- ✅ **Documentación de API**: Generación automática de documentación interactiva con **Swagger/OpenAPI**.

## 👥 Roles y Funcionalidades

### 1. Administrador (`ROLE_ADMIN`)
- **Gestión de Usuarios**: Supervisa y administra todos los usuarios (proveedores y clientes).
- **Control Total**: Accede a historiales completos de alquileres, pagos y reportes de daños.
- **Métricas y Reportes**: Genera estadísticas de ingresos, uso de equipos y rentabilidad.

### 2. Proveedor (`ROLE_PROVEEDOR`)
- **Gestión de Inventario**: Publica y administra sus herramientas, definiendo costos y disponibilidad.
- **Gestión de Reservas**: Acepta o rechaza solicitudes de alquiler de sus equipos.
- **Seguimiento**: Confirma devoluciones, reporta daños y gestiona la facturación de sus alquileres.

### 3. Cliente (`ROLE_CLIENTE`)
- **Exploración y Búsqueda**: Navega por el catálogo de herramientas, filtra por disponibilidad y consulta precios.
- **Proceso de Alquiler**: Realiza reservas, selecciona fechas y efectúa pagos en línea.
- **Historial Personal**: Accede a su historial de alquileres y gestiona sus datos.

## 🏗️ Arquitectura

El proyecto sigue los principios de **Arquitectura Hexagonal (Puertos y Adaptadores)** para desacoplar la lógica de negocio de los detalles de implementación.

```
src/main/java/com/bkseducate/securityapp/
├── domain/                    # Capa de dominio (lógica pura, sin dependencias externas)
│   ├── model/                # Entidades y objetos de valor (User, Role, Tool)
│   ├── ports/                # Interfaces (puertos) que definen la comunicación
│   └── exceptions/           # Excepciones específicas del dominio
├── application/              # Capa de aplicación (orquesta los casos de uso)
│   ├── usecase/              # Implementación de los casos de uso (ej: CreateUserUseCase)
│   ├── dto/                  # Data Transfer Objects para la comunicación
│   └── mapper/               # Mappers (MapStruct) para convertir entre DTOs y Dominio
├── infrastructure/           # Capa de infraestructura (implementaciones concretas)
│   ├── security/             # Lógica de JWT, config de Spring Security
│   ├── persistence/          # Entidades JPA, repositorios y adaptadores de BD
│   └── config/               # Configuración de Beans (ej: OpenApiConfig)
└── adapters/                 # Adaptadores que conectan el exterior con la aplicación
    ├── in/                   # Adaptadores de entrada (ej: Controladores REST)
    └── out/                  # Adaptadores de salida (ej: Implementación de repositorios)
```

## 🛠️ Tecnologías Utilizadas

- **Backend**:
  - Java 21
  - Spring Boot 3.3.0
  - Spring Security 6 (con JWT)
  - Spring Data JPA / Hibernate
  - MySQL 8.0
  - MapStruct
- **Documentación**:
  - SpringDoc OpenAPI (Swagger UI)
- **Build Tool**:
  - Maven

## 📋 Requisitos Previos

- Java 21 o superior.
- Maven 3.6 o superior.
- MySQL 8.0 o superior.

## 🔧 Configuración e Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/tu-repositorio.git
    cd tu-repositorio
    ```

2.  **Configurar la base de datos:**
    Asegúrate de tener una instancia de MySQL en ejecución. La aplicación puede crear la base de datos automáticamente en el entorno de desarrollo.

    Si prefieres crearla manualmente:
    ```sql
    CREATE DATABASE toolscat_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    ```

3.  **Configurar variables de entorno:**
    El proyecto utiliza perfiles de Spring (`dev`, `prod`). Para desarrollo, puedes configurar las siguientes variables de entorno:

    ```bash
    export SPRING_PROFILES_ACTIVE=dev
    export DB_URL=jdbc:mysql://localhost:3306/toolscat_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    export DB_USERNAME=root
    export DB_PASSWORD=tu_password_de_mysql
    export JWT_SECRET=este-es-un-secreto-muy-largo-y-seguro-para-desarrollo
    ```
    > ⚠️ **Importante**: `JWT_SECRET` debe ser una cadena larga y aleatoria, especialmente en producción.

## 🏃 Ejecución

1.  **Compilar el proyecto:**
    ```bash
    mvn clean install
    ```

2.  **Ejecutar la aplicación:**
    ```bash
    mvn spring-boot:run
    ```

La aplicación estará disponible en `http://localhost:8080`.

## 📖 Documentación de la API (Swagger)

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de la API a través de Swagger UI.

➡️ **URL de Swagger UI:** http://localhost:8080/swagger-ui.html

Desde esta interfaz podrás:
- Visualizar todos los endpoints disponibles, agrupados por controlador.
- Ver los modelos de datos (DTOs) de entrada y salida.
- Probar los endpoints directamente, incluyendo aquellos que requieren autenticación.

Para probar endpoints protegidos, primero obtén un `accessToken` desde `POST /auth/login` y luego autoriza tus peticiones en Swagger haciendo clic en el botón **"Authorize"**.

## 📡 Endpoints Principales

A continuación se muestran los endpoints de autenticación y gestión de usuarios. Los endpoints específicos del negocio (herramientas, alquileres, etc.) pueden ser explorados en detalle a través de Swagger.

### Públicos (No requieren autenticación)

- `POST /auth/register`: Registro de un nuevo usuario (cliente o proveedor).
- `POST /auth/login`: Inicio de sesión para obtener tokens de acceso.
- `POST /auth/refresh`: Refresca un `accessToken` expirado usando un `refreshToken`.

### Protegidos (Requieren `Bearer Token`)

- `GET /auth/me`: Obtiene los datos del usuario autenticado.
- `PUT /auth/change-password`: Permite al usuario cambiar su propia contraseña.
- `POST /auth/logout`: Cierra la sesión invalidando el `refreshToken`.
- `PUT /users/{userId}/roles`: Asigna un rol a un usuario (requiere `ROLE_ADMIN`).

### Ejemplo de uso con `cURL`

1.  **Registrar un usuario:**
    ```bash
    curl -X POST http://localhost:8080/auth/register \
      -H "Content-Type: application/json" \
      -d '{
        "email": "cliente@example.com",
        "password": "password123"
      }'
    ```

2.  **Iniciar sesión:**
    ```bash
    curl -X POST http://localhost:8080/auth/login \
      -H "Content-Type: application/json" \
      -d '{
        "email": "cliente@example.com",
        "password": "password123"
      }'
    ```
    *Respuesta:*
    ```json
    {
      "accessToken": "ey...",
      "refreshToken": "a1b2c3d4-...",
      "tokenType": "Bearer",
      "expiresIn": 900
    }
    ```

3.  **Acceder a un recurso protegido:**
    ```bash
    curl -X GET http://localhost:8080/auth/me \
      -H "Authorization: Bearer ey..."
    ```

## 🧪 Pruebas

Para ejecutar la suite de pruebas unitarias y de integración, utiliza el siguiente comando:

```bash
mvn test
```

## 📚 Principios y Buenas Prácticas

- **Arquitectura Hexagonal**: Separación clara entre el `QUÉ` (dominio) y el `CÓMO` (infraestructura).
- **Domain-Driven Design (DDD)**: Un modelo de dominio rico que encapsula la lógica y las reglas del negocio.
- **SOLID**: Principios aplicados para crear un software robusto, mantenible y extensible.
- **Clean Code**: Código legible, bien documentado y fácil de entender.
- **Security Best Practices**: Contraseñas hasheadas con BCrypt, tokens JWT con expiración corta, refresh tokens revocables y validación de entradas.

## 📄 Licencia

Este proyecto se distribuye bajo una licencia de uso educativo y demostrativo.
