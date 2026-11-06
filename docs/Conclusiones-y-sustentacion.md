# Conclusiones y sustentación del Laboratorio 09

**Estudiante** — Jara Ayala Ivan Rodrigo

## Cinco conclusiones

1. Separé el sistema en tres microservicios. Cliente guarda los datos de contacto, Equipo registra lo que se deja para reparar y Orden de servicio registra el trabajo. Cada uno usa su propia base de datos.
2. Usé entidades y repositorios de Spring Data JPA para guardar, buscar, actualizar y eliminar registros sin tener que escribir una consulta SQL para cada operación.
3. Usé los DTO para validar lo que recibe la API. Cuando envié un costo estimado negativo, la respuesta indicó que debía corregir ese campo.
4. Con `PATCH` cambié el estado de una orden de `RECIBIDO` a `EN_REPARACION`. Después consulté la misma orden y comprobé que seguía con el mismo equipo, la misma descripción y el mismo costo.
5. Con los perfiles `dev` y `prod` pude usar el mismo código con configuraciones distintas. En desarrollo, Hibernate puede actualizar las tablas y en producción comprueba que el esquema ya esté creado.

## Dificultad y solución

Una dificultad que tuve que resolver fue cómo comprobar los IDs si cada servicio tenía su propia base de datos. Hice que Equipo consulte a Cliente antes de guardar un equipo y que Orden consulte a Equipo antes de registrar una orden. Así, si envío un ID que no existe, la API responde `404` y me dice qué registro no encontró.

## Sustentación de 5 a 8 minutos

### Qué hice y por qué lo separé

En este laboratorio hice un sistema para un centro técnico. Pensé en lo que pasa cuando alguien deja una laptop para reparar. Primero registro al cliente, después registro el equipo que trajo y finalmente creo la orden con el problema reportado y el costo estimado. Por eso dividí el sistema en Cliente, Equipo y Orden de servicio.

### Cómo guardé los datos

En phpMyAdmin tengo tres bases. En la base de Equipo guardo el `idCliente` y en la de Orden guardo el `idEquipo`. Cuando registro una orden, mi servicio consulta por HTTP si ese equipo existe antes de guardarla. De esa manera, cada servicio trabaja con sus propias tablas.

### Cómo funciona el código de Orden

En IntelliJ, el `OrdenServicioController` recibe la petición. `OrdenServicioRequest` define los datos que necesito y las validaciones que deben pasar. En `OrdenServicioService` compruebo que el equipo exista y, si todo está correcto, uso `OrdenServicioRepository` para guardar la orden en su base.

### Qué comprobé en Postman

Primero creé una orden con estado `RECIBIDO` y obtuve `201 Created`. Luego envié un `PATCH` con el estado `EN_REPARACION` y obtuve `200 OK`. Por último, consulté la orden por su ID y comprobé que el cambio quedó guardado.

También probé qué pasa con datos incorrectos. Cuando envié un costo negativo, obtuve `400 Bad Request` y la respuesta señaló `costoEstimado`. Cuando envié el ID de un equipo que no existía, obtuve `404 Not Found`.

## Respuesta para la pregunta del docente

Separé los servicios porque cada uno se encarga de una parte distinta del mantenimiento. Guardé los datos con Spring Data JPA y cada servicio usa su propia base. Si llega un dato inválido, las validaciones del DTO hacen que la API devuelva `400` con el campo que se debe corregir. Si el dato tiene el formato correcto pero el ID no existe, la API devuelve `404`.
