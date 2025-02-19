### **Documento de Arquitectura \- Aniview**

### **Instalación**

Para la instalación y puesta en marcha del proyecto necesitaras los archivos de los siguientes repositorios:

- [AniView](https://github.com/GiovanniJMDev/Aniview)
###
- [AniViewRestAPI](https://github.com/GiovanniJMDev/AniviewRestApi)
###
- [AniViewAuthUsers](https://github.com/GiovanniJMDev/AniviewAuthUsers)
###

Ya con los repositorios descargados, ejecutamos los dos backends, AniViewRestAPI y AniviewAuthUsers.

Para ejecutar el FrontEnd, con el repositorio ya descargado vamos a abrirlo
en nuestro IDE y ejecutamos los siguientes comandos:

```bash
cd aniview

npm install

npm run dev
```

### Testear las funciones de la API en Bruno

En los dos backends AniviewAuthUsers y AniviewRestApi tenemos carpetas de nombre http, si utilizamos la aplicación Bruno podremos precargar las pruebas a la API directamente.

En el caso de la API de autenticación abrimos Bruno le damos a Collection > Open Collection > Abrimos la carpeta http

Para la API Rest del Front seguimos los mismos pasos pero esta vez tenemos que abrir la carpeta de mock-files.

Y ya tendremos lo necesario para testear las funciones de la API

#### **1\. Introducción**

**AniviewAuthUsers** es una aplicación Java desarrollada con **Spring Boot**, diseñada para gestionar la autenticación de usuarios utilizando **tokens JWT** (JSON Web Tokens). La estructura sigue una arquitectura modular basada en el patrón **MVC** (Modelo-Vista-Controlador) para separar claramente las responsabilidades.

La aplicación tiene como objetivo proporcionar una base de datos de series de animación y la capacidad para que los usuarios gestionen su progreso en estas series, dejando un registro de las que han visto, desean ver, han dejado de ver o están viendo. Además, los usuarios pueden calificar las series, lo que contribuye a la calificación promedio basada en las valoraciones de otros usuarios.

El sistema incluye también una función llamada **Anipo**, que le permite al usuario recibir una recomendación aleatoria de serie dentro de un género de su elección.

#### **1.2 Tecnologías Principales**

* **Framework**: Spring Boot 3.4.0  
* **JDK**: Java 21  
* **Base de Datos**: PostgreSQL (via Supabase)  
* **ORM**: Hibernate/JPA  
* **Seguridad**: Spring Security  
* **Servicios Externos**: Groq AI API  
* **Gestor de Dependencias**: Maven

  ---

  #### **2\. Estructura del Proyecto**

### 

    └───authusers
    │   AniviewAuthUsersApplication.java
    │   
    ├───config
    │       CorsConfig.java
    │       SecurityConfig.java
    │
    ├───controller
    │       AuthController.java
    │
    ├───dto
    │       LoginRequest.java
    │       RegisterRequest.java
    │
    ├───entity
    │       Auth.java
    │       User.java
    │
    ├───exception
    ├───repository
    │       AuthRepository.java
    │       UserRepository.java
    │
    ├───security
    │       JWTAuthorizationFilter.java
    │       JWTUtil.java
    │
    └───service
            AuthService.java
            AuthTokenService.java


### 

### 

    └───AniviewApplication.java
    │   
    ├───config
    │       ApiKeysConfig.java
    │       JacksonConfig.java
    │       SecurityConfig.java
  
    ├───controller
    │       AIChatController.java
    │       AnimeController.java
    │       AnimeListController.java
    │       AnimeListItemController.java
    │       UserController.java

    ├───dto
    │       AnimeBasicDTO.java
    │       AnimeDTO.java
    │       AnimeListDTO.java
    │       AnimeListItemDetailsDTO.java
    │       AnimeListItemDTO.java
    │       AnimeListWithItemsDTO.java
    │       MeDTO.java
    │       PasswordChangeDTO.java
    │       UserDTO.java
    
    ├───entity
    │       Anime.java
    │       AnimeList.java
    │       AnimeListItem.java
    │       ListType.java
    │       User.java
    
    ├───exception
    │       GlobalExceptionHandler.java
    │       ResourceNotFoundException.java
    │       UserException.java

    ├───repository
    │       AnimeListItemRepository.java
    │       AnimeListRepository.java
    │       AnimeRepository.java
    │       UserRepository.java

    ├───security
    │       JWTAuthorizationFilter.java

    └───service
          AIChatService.java
          AnimeListItemService.java
          AnimeListService.java
          AnimeService.java
          TokenService.java
          UserService.java




### 

### **Descripción de Componentes de autenticación \-\>**

**Config**:

* **CorsConfig.java**: Configura las políticas de CORS para permitir solicitudes de diferentes orígenes.  
  * **SecurityConfig.java**: Define la configuración de seguridad de Spring, incluyendo la protección de endpoints.

  Añadir AniviewRestApi

  ---

  #### **3\. Componentes Principales**

  ##### **3.1 Capa de Controladores**

Los controladores son responsables de manejar las solicitudes REST y delegar las operaciones a los servicios correspondientes.

* **AuthController**: Controlador REST que maneja las solicitudes de autenticación (login, registro y verificación de tokens).  
* **UserController**: Controlador REST que maneja los usuarios.  
* **AnimeController**: Controlador REST que maneja los animes  
* **UserAnimeListController**: Controlador REST que maneja los usuarios  
* **AIChatController**: Controlador REST que maneja el chatbot de la aplicación

  ##### **3.2 Capa de Servicios**

Los servicios encapsulan la lógica de negocio y gestionan las operaciones requeridas por los controladores.

* **AuthService**: Lógica de negocio relacionada con la autenticación.  
* **AuthTokenService**: Servicio para la gestión de tokens en el servicio de autenticación.  
* **UserService:** Lógica del usuario.  
* **AnimeService:** Logica del anime.  
* **AnimeListService:** Lógica del manejo de las listas.  
* **UserAnimeListService:** Lógica de las listas del usuario.  
* **AIChatService:** Lógica del manejo del chatbot  
* **TokenService:** Lógica del token en la api rest

  ##### **3.3 Capa de Repositorios**

Los repositorios proporcionan acceso a la base de datos utilizando **JpaRepository**, y permiten definir consultas personalizadas.

* **AuthRepository**: Interfaz que extiende JpaRepository para realizar operaciones CRUD sobre la entidad Auth.  
* **UserRepository**: Interfaz que extiende JpaRepository para realizar operaciones CRUD sobre la entidad Auth.  
* **AnimeListItemRepository:** Interfaz que extiende JpaRepository para realizar operaciones CRUD sobre la entidad AnimeListItem.  
* **AnimeListRepository:** Interfaz que extiende JpaRepository para realizar operaciones CRUD sobre la entidad AnimeList.

  ##### **3.4 Entidades**

Las principales entidades del dominio son:

* **User**: Representa la entidad de usuario.  
* **Auth**: Representa la entidad de autenticación.  
* **Anime**: Entidad que representa las series de anime.  
* **UserAnimeList**: Relaciona a los usuarios con las series de anime que están viendo o han visto.  
* **ListType** (Enum): Enum para definir el tipo de lista (viendo, visto, por ver, etc.).  
* **AnimeListItem**  
  ---

  #### **4\. Integración con Servicios Externos**

**4.1 Integración con Groq AI** El servicio **AIChatService** se encarga de la integración con la API de **Groq AI**, proporcionando funcionalidades avanzadas para el chatbot **AniBot**. Este chatbot permite interactuar sobre el universo del anime, ofreciendo información sobre series, personajes, recomendaciones y curiosidades del mundo del anime. Gracias a la potencia de Groq AI, AniBot puede generar respuestas contextuales y adaptarse a las preferencias del usuario, mejorando la experiencia con el tiempo.

---

#### **5\. Manejo de DTOs**

Se utilizan **DTOs** (Data Transfer Objects) para transferir datos entre capas.

Ejemplos:

* **UserDTO**: Proporciona información básica del usuario.  
* **AnimeBasicDTO**: Incluye información resumida sobre el anime.  
* **MeDTO**: Recoge la información del usuario.  
* **AnimeListWithItemDTO**: Información de la lista con los animes dentro  
* **AnimeListDTO**: Incluye la información de las listas.  
* **AnimeListDetailDTO**: Incluye los detalles de las listas.  
* **AnimeDTO**: Incluye información sobre los animes.  
* **AnimeListItemDTO**: Contiene la información de los animes que están en listas  
* **AnimeBasicDTO**: Información menos detallada sobre el anime

  ---

  #### **6\. Manejo de Excepciones**

**6.1 Jerarquía de Excepciones** El sistema implementa un **manejador global de excepciones** para capturar y procesar errores de manera centralizada, garantizando una respuesta coherente y evitando fallos no controlados. Además, se emplean manejadores específicos para errores puntuales, como validaciones de entrada o errores en la base de datos.

---

#### **7\. Configuración**

**7.1 Configuración de Base de Datos** La base de datos está configurada para **PostgreSQL** a través de **Supabase**, proporcionando una plataforma de gestión en la nube que facilita la escalabilidad y seguridad de los datos.

**7.2 Configuración de Seguridad**

* **Spring Security**: Se utiliza para asegurar los endpoints, incluyendo:  
  * Desactivación temporal de CSRF.  
  * Validación de entradas mediante JWT almacenados en cookies.  
  * Encriptación de contraseñas.  
  * Control de acceso mediante CORS.

  ---

  #### **8\. Aspectos de Rendimiento**

**8.1 Configuración del Pool de Conexiones** Se utiliza **HikariCP** para optimizar el manejo de conexiones a la base de datos.

**8.2 Lazy Loading** Se emplea **Lazy Loading** en relaciones entre entidades utilizando **@OneToMany** y **@ManyToOne**.

---

#### **9\. Consideraciones de Seguridad**

* **Validación exhaustiva de datos de entrada.**  
* **Control de acceso basado en roles** (pendiente de implementación).

  ---

  #### **10\. Puntos de Extensión**

**10.1 Áreas de Mejora Potencial**

* Implementación de **caché** para datos frecuentemente accedidos.  
* **WebSockets** para notificaciones en tiempo real.  
* Integración con **APIs de streaming** para acceder directamente a contenidos de series.

  ---

  #### **11\. Herramientas de Desarrollo**

* **IDE recomendado**: VS Code, Spring Tool Suite o IntelliJ IDEA.  
* **Herramientas de prueba**: Bruno/Postman.  
* **Control de versiones**: Git.  
* **Gestor de dependencias**: Maven.  
* **Base de datos**: PostgreSQL.  
* **Despliegue de base de datos**: Supabase.  
* **Analizador de código**: SonarQube.  
* **Plataforma de despliegue de frontend**: Vercel.  
* **Plataforma de despliegue de backend**: Render.  
* **Contenedor de imágenes**: Docker.
