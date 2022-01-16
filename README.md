# Act1_Servicios Web

En este proyecto se ha creado un servicio web que constará de dos enpoints que persitirá los datos en un fichero en memoria.

## Enpoint 1 📋

Este endpoint será el encargado de recibir una cadena de texto del cliente y añadirla a un fichero que persistirá en memoria.

## Enpoint 2 📋

Este endpoint le permitira al cliente contabilizar cuantas ocurrencias de una palabra hay en el fichero, omitiendo las mayúsculas y los acentos.

## Construido con  🛠️

* Intellij IDEA - El IDE utilizado.
* Postman - Cliente HTTP que nos da la posibilidad de hacer test.
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
Para iniciar el servidor que recibira las peticiones hay que ejecutar la clase FileServerAplication.
Si queremos enviarle peticiones al servidor podemos usar postman con las siguientes rutas:
* http://localhost:12345/file/write?str=your_text con el método POST
* http://localhost:12345/file/read?str=your_text con el método GET

## Autores ✒️
* Javier Barón Pérez - (https://github.com/jabaron56)
* Ismael De Gregorio López - (https://github.com/Lufram)
* Alberto Lozano Gómez - (https://github.com/Tachenko)
