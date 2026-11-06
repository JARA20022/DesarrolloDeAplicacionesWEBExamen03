# Examen 03 de Desarrollo de Aplicaciones Web

**Estudiante** — Iván Rodrigo Jara Ayala  
**Laboratorio 09** — Gestión de mantenimiento de equipos

## Qué desarrollé

Implementé tres microservicios con Spring Boot. Cliente registra los datos de contacto. Equipo registra el código, tipo, marca y modelo, y comprueba que el cliente exista. Orden de servicio registra la fecha de ingreso, la descripción, el costo estimado y el estado del mantenimiento. Cada servicio tiene su propia base de datos.

## Requisitos

- JDK 17 e IntelliJ IDEA.
- XAMPP con Apache y MySQL iniciados para acceder a phpMyAdmin.
- Postman para ejecutar la colección de pruebas.
- Conexión a Internet para descargar las dependencias en la primera compilación. El proyecto incluye Maven Wrapper.

## Servicios y bases de datos

| Servicio | Puerto | Base de datos | Ruta |
| --- | --- | --- | --- |
| Cliente | 8081 | `bd_cliente` | `/clientes` |
| Equipo | 8082 | `bd_equipo` | `/equipos` |
| Orden de servicio | 8083 | `bd_orden_servicio` | `/ordenes` |

## Cómo ejecutar

1. Abre [phpMyAdmin](http://localhost/phpmyadmin/) e importa [el script SQL](sql/01-crear-bases-y-tablas.sql) para crear las tres bases y sus tablas.
2. Abre en IntelliJ la carpeta que contiene el `pom.xml` principal y selecciona JDK 17.
3. Ejecuta `ClienteApplication`, `EquipoApplication` y `OrdenServicioApplication`. También puedes iniciar los tres desde PowerShell con `.\iniciar.cmd`.
4. Espera a que aparezca `Started` en las tres consolas. Puedes comprobar el arranque con un GET a `http://localhost:8081/clientes`, `http://localhost:8082/equipos` y `http://localhost:8083/ordenes`.

El perfil `dev` usa por defecto MySQL local en el puerto 3306, con el usuario `root` y contraseña vacía. Si tu instalación es distinta, configura `DB_PORT`, `DB_USER` o `DB_PASSWORD`. El perfil `prod` usa `DB_URL`, `DB_USER` y `DB_PASSWORD` de cada servicio y valida que las tablas ya existan. En `prod`, Equipo requiere `CLIENTE_SERVICE_URL` y Orden requiere `EQUIPO_SERVICE_URL`.

## Pruebas en Postman

Importa [la colección del Lab 09](postman/Lab09-Mantenimiento.postman_collection.json) y ejecuta las peticiones en orden. La colección guarda los identificadores generados para usarlos en las siguientes peticiones.

| Petición | Resultado esperado |
| --- | --- |
| 09 Crear orden recibida | `201 Created` y estado `RECIBIDO` |
| 10 Cambiar orden a EN_REPARACION | `200 OK` y estado `EN_REPARACION` |
| 11 Consultar el estado guardado | `200 OK` y el mismo ID con el estado actualizado |
| 19 Orden con costo negativo | `400 Bad Request` para el campo `costoEstimado` |
| 20 Orden con equipo inexistente | `404 Not Found` |

## Evidencias

- [Diagrama de microservicios y bases de datos](docs/diagrama-microservicios.svg)
- [Resultados de las pruebas](docs/Resultados-de-prueba.md)
- [Cinco conclusiones y guía de sustentación](docs/Conclusiones-y-sustentacion.md)