package pe.tecsup.mantenimiento.cliente.services;

import pe.tecsup.mantenimiento.cliente.dto.ClienteRequest;
import pe.tecsup.mantenimiento.cliente.dto.ClienteResponse;
import pe.tecsup.mantenimiento.cliente.model.Cliente;
import pe.tecsup.mantenimiento.cliente.repository.ClienteRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponse> listar() {
        return repository.findAll(Sort.by("id")).stream()
            .map(ClienteResponse::desde)
            .toList();
    }

    public ClienteResponse buscar(Long id) {
        return ClienteResponse.desde(obtener(id));
    }

    public ClienteResponse crear(ClienteRequest datos) {
        Cliente registro = new Cliente(
            datos.nombres().trim(),
            datos.apellidos().trim(),
            datos.correo().trim(),
            datos.telefono().trim()
        );
        return ClienteResponse.desde(repository.save(registro));
    }

    @Transactional
    public ClienteResponse actualizar(Long id, ClienteRequest datos) {
        Cliente registro = obtener(id);
        registro.actualizar(
            datos.nombres().trim(),
            datos.apellidos().trim(),
            datos.correo().trim(),
            datos.telefono().trim()
        );
        return ClienteResponse.desde(registro);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private Cliente obtener(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No se encontró al cliente con ID " + id));
    }
}
