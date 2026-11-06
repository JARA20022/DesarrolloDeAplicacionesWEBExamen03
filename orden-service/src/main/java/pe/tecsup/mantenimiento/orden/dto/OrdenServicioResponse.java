package pe.tecsup.mantenimiento.orden.dto;

import pe.tecsup.mantenimiento.orden.model.OrdenServicio;
import pe.tecsup.mantenimiento.orden.model.EstadoOrden;
import java.time.LocalDate;
import java.math.BigDecimal;

public record OrdenServicioResponse(
    Long id,
    Long idEquipo,
    LocalDate fechaIngreso,
    String descripcion,
    BigDecimal costoEstimado,
    EstadoOrden estado
) {
    public static OrdenServicioResponse desde(OrdenServicio registro) {
        return new OrdenServicioResponse(
            registro.getId(),
            registro.getIdEquipo(),
            registro.getFechaIngreso(),
            registro.getDescripcion(),
            registro.getCostoEstimado(),
            registro.getEstado()
        );
    }
}
