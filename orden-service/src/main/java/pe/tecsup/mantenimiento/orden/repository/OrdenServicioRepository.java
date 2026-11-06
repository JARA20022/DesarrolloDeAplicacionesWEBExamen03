package pe.tecsup.mantenimiento.orden.repository;

import pe.tecsup.mantenimiento.orden.model.OrdenServicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {
}
