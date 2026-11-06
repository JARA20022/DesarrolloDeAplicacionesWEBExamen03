package pe.tecsup.mantenimiento.equipo.dto;

import pe.tecsup.mantenimiento.equipo.model.Equipo;


public record EquipoResponse(
    Long id,
    String codigo,
    String tipo,
    String marca,
    String modelo,
    Long idCliente
) {
    public static EquipoResponse desde(Equipo registro) {
        return new EquipoResponse(
            registro.getId(),
            registro.getCodigo(),
            registro.getTipo(),
            registro.getMarca(),
            registro.getModelo(),
            registro.getIdCliente()
        );
    }
}
