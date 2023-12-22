# cloudStorage
Aplicacion realizada con Spring Boot. Permite a cada usuario crear o eliminar carpetas en las cuales se pueden almacenar archivos.

# funcionalidades
 - Creacion de usuarios.
 - Cada usuario posee una main folder.
 - Cada usuario puede crear y eliminar carpetas.
 - Cada usuario puede subir y eliminar archivos a sus carpetas (limitacion de 10MB por archivo).
 - Cada usuario puede ver sus carpetas y archivos.
 - Los usuarios no puede interactuar con las carpetas/archivos de otros usuarios.
 
# security
 - Se integro SpringSegurity a la aplicacion.
 - Se utilizo jwtoken para crear las sesiones de usuario.
 - Posee autenticacion y autorizacion de usuarios.

# swagger
La aplicacion se encuentra integada con swagger lo que permite utilizar y ver los endpoint de la restApi accediendo a: http://localhost:8080/swagger-ui/index.html

# ejecucion
Una vez ejecutada la aplicacion, el acceso se realiza a travez de: http://localhost:8080
La base de datos corre sobre el puerto 3306 con el nombre de cloudStorage.

# docker
Se encuentra docker integrado en la aplicacion.
Con el comando "docker compose up" se inicializa autimaticamente la aplicacion.
Tener en cuenta que se puede requerir permisos de superusuario dependiendo la configuracion del sistema operativo donde esta corriendo.
Deben estar libres los siguientes puertos:
 - 8080: puerto donde corre la aplicacion
 - 3306: puerto donde corre la base de datos
Los puertos pueden ser modificados en el archivo "docker-compose.yml"

