# Aplicación Pokémon en Android

Esta es una aplicación nativa desarrollada en **Kotlin** con **Jetpack Compose** que consume la **PokéAPI** para mostrar información de los primeros 100 Pokémon.

---

## Arquitectura

Se utiliza el patrón **MVVM** con separación en capas:

- **Data Layer**  
  Modelos de datos y consumo de la API con Retrofit y Gson.  

- **Domain Layer**  
  Lógica de negocio en el ViewModel, gestión de estados con StateFlow y corrutinas.  

- **Presentation Layer**  
  UI con Composables, pantallas de lista y detalle, navegación con Navigation Compose.  

---

## Componentes Técnicos

- Retrofit y Gson para peticiones HTTP y parsing JSON.  
- Corrutinas para asincronía.  
- StateFlow para flujos reactivos y manejo de estados (loading, success, error).  
- Coil para carga asíncrona de imágenes.  

---

## Interfaz de Usuario

- **MainScreen**  
  Lista de 100 Pokémon en LazyColumn con tarjetas e imágenes.  

- **DetailScreen**  
  Muestra imágenes front/back normales y shiny, con manejo de placeholders y navegación hacia atrás.  

- Estilo basado en Material 3, con TopAppBar y colores consistentes.  

---

## Navegación

- NavHost con rutas para pantalla principal y detalle.  
- Paso de parámetros entre pantallas y control del back stack.  

---

## Integración con PokéAPI

**Endpoints utilizados**  
- Lista de Pokémon: `https://pokeapi.co/api/v2/pokemon?limit=100`  
- Detalle de Pokémon: `https://pokeapi.co/api/v2/pokemon/{id}`  

**Datos consumidos**  
- Nombre e ID  
- Sprites normales y shiny (front/back)  
- Tipos de Pokémon  
- Altura y peso  

---

## Flujo de Datos

1. Se carga la lista desde el ViewModel.  
2. API → Retrofit → JSON → data classes.  
3. StateFlow notifica cambios a la UI.  
4. Al seleccionar un Pokémon, se navega a la pantalla de detalle y se carga su información.  

---

## Manejo de Estados

- Estados: Loading, Success, Error, Empty.  
- Uso de null safety en propiedades opcionales.  

---

## Tecnologías Utilizadas

- Jetpack Compose  
- Navigation Compose  
- Retrofit + Gson  
- Coil  
- ViewModel + StateFlow  
- Corrutinas  

---

Uso de null safety en propiedades opcionales.

Tecnologías

Jetpack Compose, Navigation Compose, Retrofit, Gson, Coil, ViewModel, StateFlow y corrutinas.

---
## Preview de la Applicación
<img width="364" height="807" alt="image" src="https://github.com/user-attachments/assets/07055dd9-4281-4adf-830a-61212e2d4a49" />
<img width="364" height="816" alt="image" src="https://github.com/user-attachments/assets/8bb1f4f6-5d48-48f9-b1c0-28a89fd9bded" />







