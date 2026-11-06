# Resultados de la prueba de aceptación

La colección de Postman se ejecutó con Newman contra los tres servicios compilados con Java 17 y MariaDB 10.11.14. Antes de iniciar se importó el SQL del proyecto.

| Perfil | Peticiones ejecutadas | Verificaciones correctas | Fallos |
| --- | ---: | ---: | ---: |
| `dev` | 30 | 64 | 0 |
| `prod` | 30 | 64 | 0 |

## Respuestas del perfil dev

### Creación de la orden

`POST /ordenes` → **201 Created**

```json
{
  "id": 1,
  "idEquipo": 1,
  "fechaIngreso": "2026-11-06",
  "descripcion": "El equipo no enciende.",
  "costoEstimado": 120.5,
  "estado": "RECIBIDO"
}
```

### Cambio a EN REPARACION

`PATCH /ordenes/1/estado` → **200 OK**

```json
{
  "id": 1,
  "idEquipo": 1,
  "fechaIngreso": "2026-11-06",
  "descripcion": "El equipo no enciende.",
  "costoEstimado": 120.5,
  "estado": "EN_REPARACION"
}
```

### Consulta del estado guardado

`GET /ordenes/1` → **200 OK**

```json
{
  "id": 1,
  "idEquipo": 1,
  "fechaIngreso": "2026-11-06",
  "descripcion": "El equipo no enciende.",
  "costoEstimado": 120.5,
  "estado": "EN_REPARACION"
}
```

### Orden con costo negativo

`POST /ordenes` → **400 Bad Request**

```json
{
  "estado": 400,
  "mensaje": "Revisa los campos indicados.",
  "campos": {
    "costoEstimado": "must be greater than or equal to 0"
  }
}
```

### Orden con equipo inexistente

`POST /ordenes` → **404 Not Found**

```json
{
  "estado": 404,
  "mensaje": "El equipo con ID 999999999 no existe.",
  "campos": {}
}
```
