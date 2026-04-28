# Gym App

Una aplicación Android moderna diseñada para gestionar rutinas de entrenamiento, ejercicios y realizar un seguimiento detallado del progreso en el gimnasio.

## 🚀 Características principales

- **Gestión de Sesiones:** Crea, edita y organiza tus rutinas (ej. Empuje/Tirón/Pierna).
- **Control de Ejercicios:** Define ejercicios personalizados para cada sesión, especificando el número de series y notas adicionales.
- **Registro en tiempo real:** Interfaz intuitiva para anotar peso y repeticiones mientras entrenas.
- **Historial de entrenamientos:** Consulta sesiones pasadas para comparar resultados y ver tu evolución.
- **Respaldo en la Nube (API):** Sección dedicada para sincronizar y consultar datos con un servidor externo mediante una API REST.

## 🛠️ Stack Tecnológico

- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) con Material Design 3.
- **Inyección de Dependencias:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).
- **Base de Datos Local:** [Room](https://developer.android.com/training/data-storage/room).
- **Networking:** [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/).
- **Navegación:** [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) con rutas serializables (Type-safe).
- **Arquitectura:** MVVM (Model-View-ViewModel).

## 📁 Estructura del Proyecto

- `com.gym.data.room`: Entidades, DAOs y base de datos local.
- `com.gym.data.retrofit`: Interfaces de servicios y modelos para la comunicación con la API.
- `com.gym.models.repositorios`: Capa de abstracción de datos (Repository Pattern).
- `com.gym.ui.features`: Implementación de pantallas y ViewModels organizada por módulos (Sesiones, Ejercicios, Historial, Registros).
- `com.gym.ui.navigation`: Definición del grafo de navegación y destinos.
- `com.gym.ui.theme`: Personalización de colores, formas y tipografía de la aplicación.

## ⚙️ Instalación y Configuración

1. Clonar el repositorio.
2. Abrir el proyecto en **Android Studio**.
3. Sincronizar el proyecto con los archivos Gradle.
4. Para la parte de la API:
   - Asegúrate de que el servidor backend esté corriendo en la IP configurada (por defecto `192.168.0.101`).
   - El archivo `AndroidManifest.xml` permite tráfico `http` (cleartext) para facilitar el desarrollo local.

## 📸 Capturas de pantalla
*(Próximamente)*

---
Desarrollado como proyecto de Desarrollo de Aplicaciones Móviles.
