package pe.tecsup.mantenimiento.cliente.dto;

import pe.tecsup.mantenimiento.cliente.model.Cliente;


public record ClienteResponse(
    Long id,
    String nombres,
    String apellidos,
    String correo,
    String telefono
) {
    public static ClienteResponse desde(Cliente registro) {
        return new ClienteResponse(
            registro.getId(),
            registro.getNombres(),
            registro.getApellidos(),
            registro.getCorreo(),
            registro.getTelefono()
        );
    }
}
