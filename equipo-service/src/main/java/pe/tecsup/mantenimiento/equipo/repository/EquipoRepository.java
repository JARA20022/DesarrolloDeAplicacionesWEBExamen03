package pe.tecsup.mantenimiento.equipo.repository;

import pe.tecsup.mantenimiento.equipo.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
