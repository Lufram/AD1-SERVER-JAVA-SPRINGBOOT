

# Spring Boot y Servicios REST.


## Requerimiento 1.

En este proyecto se va a ver como podemos implementar un servicio REST con el Framework de Spring y Spring Boot.

### SERVIDOR SUMMUN

Realizaremos un servicio REST que proporcione un servicio CRUD completo, podra ser consumido mediante un cliente como Postman y todo el intercambio de mensajes se realizara a traves de JSON.

Dicho servicio gestionara una BBDD de videojuegos simulada en memoria. Al arrancar el programa dara de alta 5 entradas preestablecidas y a partir de ahi quedará a la espera de peticiones.

### Videojuegos.

Los videojuegos tendrán un ID, un nombre, una compañía y una nota para ello crearemos la clase <b>Game</b>.

### Peticiones.

Nuestro servicio dara respuesta a las siguientes peticiones.

1. Dar de alta un videojuego.
2. Dar de baja un videojuego por ID.
3. Modificar un videojuego por ID.
4. Obtener un videojuego por ID.
5. Listar todos los videojuegos.

## Requerimiento 2.

Añadiremos una comprobación tanto al añadir como al modificar para que no nos permita  dar de alta dos videojuegos con el mismo Nombre o ID.

### Poner en marcha la aplicacion (SERVIDOR)

Arrancaremos la aplicacion desde IDE ejecuntando el archivo:

- AE3-RESTSERVICE-SPRINGBOOT/summun/src/main/java/com/summun/launcher/SummunApplication.java 

El servidor utiliza el puerto 8086.


### Añadir

Modificaremos nuestro metodo para que recorra el la lista de Videojuegos comparando con cada entrada el Nombre que hemos introducido y nos devolvera un boolean para cada uno de estos casos.

	- El Nombre no existe asi que se realiza la entrada.

	- El Nombre existe y no se realiza la entrada.


### Modificar

Modificaremos nuestro metodo para que recorra el la lista de Videojuegos comparando con cada entrada el Nombre que hemos introducido y nos devolvera un String dado que tenemos 3 casos:

	- El ID no existe por lo tanto no se realiza ninguna modificacion.

	- El ID existe pero el nombre introducido ya esta en uso asi que no se modifica.

	- El ID existe y el Nombre no esta en uso asi que se modifica el registro.



## Requerimiento 3.

Crearemos un segundo proyecto SpringBoot para realizar una aplicación desde la que el cliente podria realizar peticiones al servidor. Para ello crearemos un menu que mostrara por consola las opciones y solicitara la eleccion del usuario de forma numerica.

Cada opcion del menu solicitara los datos necesarios para poder enviar al servidor la solicitud. La clase ServiceProxyGame construira la url que se enviara al servidor para cada petición y de recoger y procesar la respuesta del servidor.

### Menu

Hemos creado un menu algo dinamico simulando que tiene una actitud acida, burlona y con poca paciencia.  Si nos equivocamos varias veces al introducir una eleccion del menú la respuesta se vuelve mas burlona, ( no se ofendan es solo una broma para hacer algo divertido todo este codigo o puede que solo se nos fundiera algun fusible mietras programabamos, diculpen las molestias /smile )

### Poner en marcha la aplicacion (CLIENTE)

Arrancaremos la aplicacion desde IDE ejecuntando el archivo:

- AE3-RESTSERVICE-SPRINGBOOT/gamesClient/src/main/java/com/summun/client/GamesClientApplication.java/

!!! IMPORTANTE ARRANCAR PRIMERO EL SERVIDOR.

El CLIENTE utiliza el puerto 8081.


