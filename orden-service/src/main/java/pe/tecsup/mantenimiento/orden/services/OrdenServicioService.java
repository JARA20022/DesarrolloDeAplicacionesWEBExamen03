package pe.tecsup.mantenimiento.orden.services;

import pe.tecsup.mantenimiento.orden.client.EquipoClient;
import pe.tecsup.mantenimiento.orden.dto.EstadoOrdenRequest;
import pe.tecsup.mantenimiento.orden.dto.OrdenServicioRequest;
import pe.tecsup.mantenimiento.orden.dto.OrdenServicioResponse;
import pe.tecsup.mantenimiento.orden.model.OrdenServicio;
import pe.tecsup.mantenimiento.orden.repository.OrdenServicioRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrdenServicioService {

    private final OrdenServicioRepository repository;
    private final EquipoClient equipoClient;

    public OrdenServicioService(OrdenServicioRepository repository, EquipoClient equipoClient) {
        this.repository = repository;
        this.equipoClient = equipoClient;
    }

    public List<OrdenServicioResponse> listar() {
        return repository.findAll(Sort.by("id")).stream()
            .map(OrdenServicioResponse::desde)
            .toList();
    }

    public OrdenServicioResponse buscar(Long id) {
        return OrdenServicioResponse.desde(obtener(id));
    }

    public OrdenServicioResponse crear(OrdenServicioRequest datos) {
        equipoClient.verificarExiste(datos.idEquipo());
        OrdenServicio registro = new OrdenServicio(
            datos.idEquipo(),
            datos.fechaIngreso(),
            datos.descripcion().trim(),
            datos.costoEstimado(),
            datos.estado()
        );
        return OrdenServicioResponse.desde(repository.save(registro));
    }

    @Transactional
    public OrdenServicioResponse actualizar(Long id, OrdenServicioRequest datos) {
        OrdenServicio registro = obtener(id);
        equipoClient.verificarExiste(datos.idEquipo());
        registro.actualizar(
            datos.idEquipo(),
            datos.fechaIngreso(),
            datos.descripcion().trim(),
            datos.costoEstimado(),
            datos.estado()
        );
        return OrdenServicioResponse.desde(registro);
    }

    @Transactional
    public OrdenServicioResponse cambiarEstado(Long id, EstadoOrdenRequest datos) {
        OrdenServicio registro = obtener(id);
        registro.cambiarEstado(datos.estado());
        return OrdenServicioResponse.desde(registro);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private OrdenServicio obtener(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró la orden de servicio con ID " + id));
    }
}
