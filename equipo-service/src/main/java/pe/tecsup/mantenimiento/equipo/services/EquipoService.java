package pe.tecsup.mantenimiento.equipo.services;

import pe.tecsup.mantenimiento.equipo.client.ClienteClient;
import pe.tecsup.mantenimiento.equipo.dto.EquipoRequest;
import pe.tecsup.mantenimiento.equipo.dto.EquipoResponse;
import pe.tecsup.mantenimiento.equipo.model.Equipo;
import pe.tecsup.mantenimiento.equipo.repository.EquipoRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EquipoService {

    private final EquipoRepository repository;
    private final ClienteClient clienteClient;

    public EquipoService(EquipoRepository repository, ClienteClient clienteClient) {
        this.repository = repository;
        this.clienteClient = clienteClient;
    }

    public List<EquipoResponse> listar() {
        return repository.findAll(Sort.by("id")).stream()
            .map(EquipoResponse::desde)
            .toList();
    }

    public EquipoResponse buscar(Long id) {
        return EquipoResponse.desde(obtener(id));
    }

    public EquipoResponse crear(EquipoRequest datos) {
        clienteClient.verificarExiste(datos.idCliente());
        Equipo registro = new Equipo(
            datos.codigo().trim(),
            datos.tipo().trim(),
            datos.marca().trim(),
            datos.modelo().trim(),
            datos.idCliente()
        );
        return EquipoResponse.desde(repository.save(registro));
    }

    @Transactional
    public EquipoResponse actualizar(Long id, EquipoRequest datos) {
        Equipo registro = obtener(id);
        clienteClient.verificarExiste(datos.idCliente());
        registro.actualizar(
            datos.codigo().trim(),
            datos.tipo().trim(),
            datos.marca().trim(),
            datos.modelo().trim(),
            datos.idCliente()
        );
        return EquipoResponse.desde(registro);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private Equipo obtener(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró el equipo con ID " + id));
    }
}
