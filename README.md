# 📌 Gestión de Voluntariados y Actividades Sociales

Este proyecto es una aplicación de consola en **Java** diseñada para gestionar proyectos de voluntariado y actividades sociales. La aplicación permite la creación de iniciativas, la gestión de actividades y la asignación de voluntarios mediante un sistema de roles bien definido.

---

## 📖 Descripción del Proyecto

El propósito de este sistema es **facilitar la administración de actividades sociales**, permitiendo que los usuarios puedan registrarse, organizar eventos y participar activamente en iniciativas de voluntariado.  

---

## 🚀 Funcionalidades Principales

✔ **Gestión de usuarios con roles diferenciados:**  
   - Administradores  
   - Creadores de iniciativas  
   - Voluntarios  

✔ **Autenticación segura** con almacenamiento de contraseñas hasheadas con **BCrypt**  
✔ **Persistencia de datos** en archivos **XML**  
✔ **Gestión de actividades** y asignación de voluntarios  
✔ **Sistema de premios** para incentivar la participación activa de los voluntarios  
✔ **Menú interactivo con colores** para mejorar la experiencia en consola  

---

## 🛠️ Diseño

### 🔹 Modelo de Datos  
El sistema sigue un modelo relacional donde las entidades principales son:  

- **Usuarios:** Representan a los administradores, creadores y voluntarios.  
- **Iniciativas:** Proyectos sociales organizados por los creadores.  
- **Actividades:** Eventos específicos dentro de cada iniciativa, donde los voluntarios pueden participar.  
- **Premios:** Recompensas otorgadas a los voluntarios según su participación.  

---

### 🔹 Diagrama Entidad-Relación (ER)  
<img width="543" alt="image" src="https://github.com/user-attachments/assets/8e8d9506-b717-46a3-9a66-aed4560c06de" />


### 🔹 Diagrama de Clases  

![Proyecto2EvalGrupo3 Diagrama de clases](https://github.com/user-attachments/assets/24fd4182-d0a1-4938-9c13-e9713a017872)

El sistema sigue el **paradigma de programación orientada a objetos (POO)**, con clases organizadas en **controladores, vistas y modelos**, facilitando la escalabilidad del código.

---

## ⚙️ Tecnologías Utilizadas  

🔹 **Java** (JDK 17+)  
🔹 **XML** (Para almacenamiento de datos)  
🔹 **BCrypt** (Para hash de contraseñas)  

---

## 📌 Instalación y Uso  

### 1️⃣ **Clona este repositorio:**   git clone git@github.com:Antoniodp00/Proyecto2EvalGrupo3.git
### 2️⃣ Compila y ejecuta el proyecto en tu entorno de desarrollo (IntelliJ, VS Code, Eclipse, etc.).  
### 3️⃣ Explora y gestiona iniciativas sociales desde la línea de comandos.  

---

## 🛠️ Mejoras Futuras  
- 🔹 Implementación de una interfaz gráfica (GUI) con JavaFX o Swing.  
- 🔹 Uso de bases de datos relacionales en lugar de XML para mayor escalabilidad.  
- 🔹 Sistema de notificaciones para voluntarios y organizadores.  
- 🔹 Mejoras en el sistema de premios con canjeo de recompensas.  

---

## 🤝 Aportes de ChatGPT  
En algunos puntos del desarrollo se solicitó ayuda a ChatGPT para resolver problemas específicos:  

✅ **Solución al fallo con LocalDate en XML:** Se implementó un adapter para convertir `LocalDate` a `String` y viceversa.  
✅ **Implementación de almacenamiento seguro con BCrypt.**  
✅ **Verificación y creación automática de archivos XML si no existen.**  
✅ **Añadido de colores en la interfaz de consola.**  
✅ **Mejoras en la exportación de datos y validaciones en los formularios.**  

---

## 📜 Licencia  
Este proyecto está bajo la licencia **MIT**.  

---

## ⭐ GitHub  
Si te gusta el proyecto, ¡dale una estrella en GitHub! 🚀  

