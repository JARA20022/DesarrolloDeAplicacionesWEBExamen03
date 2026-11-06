package pe.tecsup.mantenimiento.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record ClienteRequest(
    @NotBlank @Size(max = 80) String nombres,
    @NotBlank @Size(max = 80) String apellidos,
    @NotBlank @Email @Size(max = 150) String correo,
    @NotBlank @Size(max = 25) String telefono
) {
}
