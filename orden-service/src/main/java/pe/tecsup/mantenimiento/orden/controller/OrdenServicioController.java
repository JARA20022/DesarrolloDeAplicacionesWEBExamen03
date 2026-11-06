package pe.tecsup.mantenimiento.orden.controller;

import pe.tecsup.mantenimiento.orden.dto.EstadoOrdenRequest;
import pe.tecsup.mantenimiento.orden.dto.OrdenServicioRequest;
import pe.tecsup.mantenimiento.orden.dto.OrdenServicioResponse;
import pe.tecsup.mantenimiento.orden.services.OrdenServicioService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordenes")
public class OrdenServicioController {

    private final OrdenServicioService service;

    public OrdenServicioController(OrdenServicioService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrdenServicioResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public OrdenServicioResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<OrdenServicioResponse> crear(@Valid @RequestBody OrdenServicioRequest datos) {
        OrdenServicioResponse registro = service.crear(datos);
        return ResponseEntity.created(URI.create("/ordenes/" + registro.id())).body(registro);
    }

    @PutMapping("/{id}")
    public OrdenServicioResponse actualizar(@PathVariable Long id, @Valid @RequestBody OrdenServicioRequest datos) {
        return service.actualizar(id, datos);
    }

    @PatchMapping("/{id}/estado")
    public OrdenServicioResponse cambiarEstado(
        @PathVariable Long id, @Valid @RequestBody EstadoOrdenRequest datos
    ) {
        return service.cambiarEstado(id, datos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
