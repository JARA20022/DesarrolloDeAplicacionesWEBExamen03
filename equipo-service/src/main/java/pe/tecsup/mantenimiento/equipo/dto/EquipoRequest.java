package pe.tecsup.mantenimiento.equipo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record EquipoRequest(
    @NotBlank @Size(max = 30) String codigo,
    @NotBlank @Size(max = 60) String tipo,
    @NotBlank @Size(max = 60) String marca,
    @NotBlank @Size(max = 80) String modelo,
    @NotNull @Positive Long idCliente
) {
}
