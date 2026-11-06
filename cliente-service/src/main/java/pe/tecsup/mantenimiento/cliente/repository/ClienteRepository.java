package pe.tecsup.mantenimiento.cliente.repository;

import pe.tecsup.mantenimiento.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
