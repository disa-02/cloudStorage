# 📁 cloudStorage
Aplicacion realizada con Spring Boot. Permite a cada usuario crear o eliminar carpetas en las cuales se pueden almacenar archivos.

---

## ✨ Funcionalidades
- 🗂️ Cada usuario posee una main folder.
- 📁 Cada usuario puede crear y eliminar carpetas.
- 📤 Cada usuario puede subir y 🗑️ eliminar archivos en sus carpetas (📏 limitación de 10MB por archivo).
- 👁️ Cada usuario puede ver sus carpetas y archivos.
- 🚫 Los usuarios no pueden interactuar con las carpetas/archivos de otros usuarios.

---

## 🚀 Ejecución de la Demo

### 🔧 Prerrequisitos

- Tener instalado **Docker** y **docker-compose**
- Tener disponibles los puertos **8080** y **3306**

### ▶️ Para ejecutar el backend

Desde el directorio raíz `/cloudStorage`, ejecuta:

```bash
docker-compose up
```

### ▶️ Accesos a la Demo

⚙️ Backend: http://localhost:8080/swagger-ui/index.html


---