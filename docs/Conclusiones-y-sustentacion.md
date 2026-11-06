# Conclusiones y sustentación del Laboratorio 09

**Estudiante** — Jara Ayala Ivan Rodrigo

## Cinco conclusiones

1. Separar Cliente, Equipo y Orden de servicio me permitió asignar a cada microservicio una responsabilidad y una base de datos propia.
2. Con una entidad JPA y su repositorio pude guardar, buscar, actualizar y eliminar registros en las tablas sin escribir una consulta SQL para cada operación.
3. Los DTO me sirvieron para validar los datos antes de guardarlos. Por ejemplo, la API indica qué campo revisar cuando el costo estimado es negativo.
4. Con `PATCH` pude cambiar el estado de la orden enviando un solo dato. Al consultarla de nuevo, conservó su descripción, su equipo y su costo.
5. Los perfiles me permitieron usar el mismo código con dos configuraciones. En desarrollo, Hibernate puede actualizar las tablas y en producción valida las tablas ya creadas.

## Dificultad y solución

La separación de las bases deja abierta la posibilidad de que alguien envíe el ID de un cliente o de un equipo que no existe. Para resolverlo, Equipo consulta al servicio Cliente antes de guardar el equipo y Orden consulta al servicio Equipo antes de guardar la orden. Si el ID no existe, la operación devuelve `404` con un mensaje. Las pruebas de Postman incluyen ambos casos.

## Guion de sustentación de 5 a 8 minutos

| Tiempo aproximado | Qué mostrar y explicar |
| --- | --- |
| 1 minuto | Presenta el problema del centro técnico y el diagrama. Cliente guarda los datos de contacto, Equipo identifica el bien que se repara y Orden guarda el trabajo y su estado. |
| 1 minuto | Abre phpMyAdmin. Muestra las tres bases y explica que el equipo guarda `idCliente` y la orden guarda `idEquipo`. Las consultas entre servicios se hacen por HTTP. |
| 1 a 2 minutos | Abre el módulo de Orden en IntelliJ. Muestra cómo `OrdenServicioController` recibe el JSON, `OrdenServicioRequest` define los datos permitidos, `OrdenServicioService` aplica las reglas y `OrdenServicioRepository` guarda la entidad `OrdenServicio`. |
| 2 minutos | Ejecuta el flujo principal en Postman hasta la petición 11. Señala el `201` al crear la orden, el `200` del `PATCH` y el `200` del `GET` que confirma el estado `EN_REPARACION`. |
| 1 minuto | Envía la orden con costo negativo y después con un equipo inexistente. Muestra el `400` con el campo observado y el `404` con el mensaje de referencia. Cierra mostrando los archivos de perfil. |

## Respuesta para la pregunta del docente

Separé los servicios porque cada uno maneja una parte del trabajo del centro técnico. Cliente guarda los datos de la persona, Equipo guarda lo que deja para reparar y Orden guarda el servicio que se va a realizar. Cada aplicación tiene un repositorio de Spring Data JPA y guarda su entidad en su propia base. Cuando la API recibe datos inválidos, el DTO detecta el campo y el manejador de errores responde con un `400` y un mensaje. Si el ID solicitado no existe, responde con un `404`.
