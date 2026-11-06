package pe.tecsup.mantenimiento.orden.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import pe.tecsup.mantenimiento.orden.model.EstadoOrden;
import java.time.LocalDate;
import java.math.BigDecimal;

public record OrdenServicioRequest(
    @NotNull @Positive Long idEquipo,
    @NotNull LocalDate fechaIngreso,
    @NotBlank @Size(max = 500) String descripcion,
    @NotNull @PositiveOrZero @Digits(integer = 10, fraction = 2) BigDecimal costoEstimado,
    @NotNull EstadoOrden estado
) {
}
