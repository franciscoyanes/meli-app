# Challenge Android Mercado Libre

## Introducción

Esta aplicación es mi resolución para el challenge de Android Mobile. Se pidió desarrollar una aplicación para Android que, utilizando las APIs de Mercado Libre, le permita al usuario buscar y ver los detalles de distintos productos.

El desarrollo se hizo siguiendo las prácticas de [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) y [MVVM](https://www.geeksforgeeks.org/mvvm-model-view-viewmodel-architecture-pattern-in-android/).

### Tecnologías utilizadas

**Desarrollo**:

- **Kotlin**
- **Retrofit & OkHttp** para la integración de APIs REST.
- **Kotlin Coroutines** para el soporte de programación asíncrona.
- **Hilt** para la implementación de inyección de dependencias.
- **Coil** para la carga y cacheo de imágenes remotas.
- **JUnit4**, **MockK** y **MockWebServer** para el testeo.
## Instalación

#### Requisitos previos

Antes de comenzar, hay que asegurarse de tener instalado Java 17.

#### Pasos para la instalación

1. Clonar el repositorio
    ```bash
    git clone https://github.com/franciscoyanes/meli-app.git
    ```
2. Abrir el proyecto en Android Studio.
3. Asegurarse de que Java 17 esté configurado como la versión predeterminada de Java para correr el proyecto.
4. Buildear y ejecutar.

## Pantallas

La app consta de tres pantallas, como lo recomienda el ejercicio. El usuario podrá tanto avanzar como retroceder al navegar por las pantallas.

#### 1. Input de búsqueda

En esta pantalla el usuario puede ingresar el nombre o una frase de lo que desea buscar. Puede iniciar la búsqueda del texto ingresado apretando la opción de *Enter* en su teclado, que lo llevará a l siguiente pantalla.

Al intentar iniciar la búsqueda con un texto vacío no se navegará a la siguiente pantalla sino que se le mostrará al usuario un diálogo de advertencia explicándole que es estrictamente necesario que ingrese un texto para poder avanzar a la siguiente pantalla.

<p align="center">
    <img src="./assets/screen_1.gif" style="zoom:40%;" />
</p>

#### 2. Listado de productos

En esta pantalla el usuario recibirá un listado de productos acorde a su búsqueda en forma de catálogo. El catálogo estará compuesto por múltiples cartas mostrando foto y detalles productos. El usuario podrá navegar el catálogo verticalmente y seleccionar la carta del producto del que quiera ver mas detalles. Al presionar sobre la carta de un producto el usuario es llevado a la siguiente y ultima pantalla.

<p align="center">
    <img src="./assets/screen_2.gif" style="zoom:40%;" />
</p>

#### 3. Detalle de producto

En esta pantalla el usuario podrá ver mas detalles sobre el producto seleccionado.

<p align="center">
    <img src="./assets/screen_3.gif" style="zoom:40%;" />
</p>
