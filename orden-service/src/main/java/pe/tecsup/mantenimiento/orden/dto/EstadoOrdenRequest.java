package pe.tecsup.mantenimiento.orden.dto;

import jakarta.validation.constraints.NotNull;
import pe.tecsup.mantenimiento.orden.model.EstadoOrden;

public record EstadoOrdenRequest(@NotNull EstadoOrden estado) {
}
