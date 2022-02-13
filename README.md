# Act1_Servicios Web

En este proyecto se ha creado un servicio web que constará de dos enpoints que persitirá los datos en un fichero en memoria.

## Endpoint 1 📋

Este endpoint será el encargado de recibir una cadena de texto del cliente y añadirla a un fichero que persistirá en memoria.

## Endpoint 2 📋

Este endpoint le permitira al cliente contabilizar cuantas ocurrencias de una palabra hay en el fichero, omitiendo las mayúsculas y los acentos.

## Construido con  🛠️

* Maven herramienta de gestión y construcción de proyectos.
* Spring Boot - El framework utilizado.

## Sistemas Operativos💻

* Windows : Comprobado
* MacOS : No Comprobado
* Linux : No Comprobado

## Restricciones ❗
Hay algunas validaciones en la aplicación para evitar su mal funcionamiento.

* Omite los acentos a la hora de comparar las palabras en el segundo endpoint.
* Omite las mayúsculas a la hora de comparar las palabras en segundo endpoint.
* Si no hay ningún fichero creado lo genera para poder trabajar sobre él.
* El segundo endpoint no contabilizará dos palabras en una misma cadena de texto.

## Despliegue 📦
Para iniciar el servicio se debe acceder por medio del ejecutable "ejecutar.bat" que se encuentra independiente de la carpeta de la aplicación.
Si queremos enviarle peticiones al servidor podemos usar postman con las siguientes rutas:
* http://localhost:12345/file/write?str=your_text con el método POST
* http://localhost:12345/file/read?str=your_text con el método GET

## Testing 📋
Para comprobar que nuestro código funciona correctamente y sigue haciéndolo en las siguientes
versiones, hemos creado una batería de test, hemos excluido las clases que inician la aplicación
ya que todos nuestros métodos están incluidos en la clase FileController.
 

## Script 📜
Para crear el script "ejecutar.bat" hemos seguido los siguientes pasos:

1. Editamos el pom.xml añadiendo "<packaging>jar</packaging>".
2. Ejecutamos el proyecto en modo Maven Build... y en goals añadimos "clean package" antes de ejecutar.
3. Ya se nos habrá creado el .jar "client-0.0.1-SNAPSHOT.jar".
4. Ahora construiremos el ejecutable.bat que apuntará al "client-0.0.1-SNAPSHOT.jar", el cual iniciará el servicio.

## Autores ✒️
* Javier Barón Pérez - (https://github.com/jabaron56)
* Ismael De Gregorio López - (https://github.com/Lufram)
* Alberto Lozano Gómez - (https://github.com/Tachenko)
