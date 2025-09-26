Arquitectura

Patrón MVVM con separación en capas:

Data Layer: modelos de datos y consumo de la API con Retrofit y Gson.

Domain Layer: lógica de negocio en el ViewModel, gestión de estados con StateFlow y corrutinas.

Presentation Layer: UI con Composables, pantallas de lista y detalle, navegación con Navigation Compose.

Componentes técnicos

Retrofit y Gson para peticiones y parsing JSON.

Corrutinas para asincronía.

StateFlow para flujos reactivos y manejo de estados (loading, success, error).

Coil para carga de imágenes.

Interfaz de usuario

MainScreen: lista de 100 Pokémon en LazyColumn con tarjetas y sus imágenes.

DetailScreen: muestra imágenes front/back normales y shiny, con manejo de placeholders y navegación hacia atrás.

Estilo basado en Material 3, con TopAppBar y colores consistentes.

Navegación

NavHost con rutas para pantalla principal y detalle.

Paso de parámetros entre pantallas y back stack controlado.

Integración con la API

Endpoints de lista y detalle de Pokémon.

Datos consumidos: nombre, id, sprites normales y shiny, tipos, altura y peso.

Flujo de datos

Carga de lista desde el ViewModel.

API → Retrofit → JSON → data classes.

StateFlow notifica a la UI.

Al seleccionar un Pokémon, se navega a la pantalla de detalle y se carga su información.

Manejo de estados

Loading, Success, Error, Empty.

Null safety en propiedades opcionales.

Tecnologías

Jetpack Compose, Navigation Compose, Retrofit, Gson, Coil, ViewModel, StateFlow y corrutinas.

Características destacadas

Rendimiento optimizado con LazyColumn y carga perezosa de imágenes.

UX con diseño responsivo, estados visuales claros y navegación fluida.

Código mantenible y escalable gracias a la arquitectura modular y buenas prácticas.
