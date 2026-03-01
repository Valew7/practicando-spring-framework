# 🚀 Foro Hub API - En desarrollo

Este proyecto es una API REST para un sistema de foros, desarrollada como parte del challenge "Foro Hub" de Alura Latam. Permite la gestión de tópicos, respuestas y usuarios con autenticación segura.

## 📋 Estado del Proyecto
En desarrollo activo. Se están implementando las funcionalidades principales de tópicos y autenticación.

## 🛠️ Tecnologías Utilizadas
*   Java 17
*   Spring Boot 3
*   Spring Security
*   JWT (Auth0)
*   Spring Data JPA / Hibernate
*   MySQL
*   Flyway Migration
*   Lombok
*   Maven

## ⚙️ Cómo Ejecutar el Proyecto
1.  **Clonar el repositorio**
    ```bash
    git clone https://github.com/Valew7/practicando-spring-framework.git
    cd practicando-spring-framework



    
### 🔧 Cómo añadirlo a tu repositorio

Tienes dos opciones sencillas para añadir este archivo:

**Opción 1: Directamente en GitHub (más fácil)**
1.  Entra a tu repositorio: `https://github.com/Valew7/practicando-spring-framework`
2.  Haz clic en el botón verde "**Add file**" y luego en "**Create new file**".
3.  Nombra el archivo: `README.md` (exactamente así).
4.  En el gran cuadro de texto, pega el contenido del borrador que te he preparado.
5.  **¡IMPORTANTE!** Revisa y **adapta el contenido**:
    *   Cambia `TU_CONTRASEÑA` y `TU_CLAVE_SECRETA_SUPER_SEGURA` por marcadores de posición (nunca subas contraseñas reales). Puedes poner `[TU_CONTRASEÑA_DB]` y `[TU_CLAVE_SECRETA_JWT]`.
    *   Asegúrate de que los endpoints (`/auth/login`, `/topicos`) coincidan con los que realmente tienes en tu controlador.
    *   Si tu paquete principal no es `com.tuforo.forohub`, cámbialo.
6.  En la parte inferior, escribe un mensaje para el commit, por ejemplo: "docs: Añadir README.md inicial".
7.  Selecciona "**Commit directly to the main branch**" y haz clic en "**Commit new file**".

**Opción 2: Desde tu computadora con Git**
1.  En tu computadora, dentro de la carpeta de tu proyecto, crea un archivo llamado `README.md`.
2.  Abre ese archivo con un editor de texto y pega el contenido del borrador, adaptándolo como se indica arriba.
3.  Guarda el archivo.
4.  Abre una terminal en la carpeta del proyecto y ejecuta estos comandos:
    ```bash
    git add README.md
    git commit -m "docs: Añadir README.md inicial"
    git push origin main
