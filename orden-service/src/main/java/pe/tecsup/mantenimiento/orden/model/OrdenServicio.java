package pe.tecsup.mantenimiento.orden.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "ordenes")
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idEquipo;
    @Column(nullable = false)
    private LocalDate fechaIngreso;
    @Column(nullable = false, length = 500)
    private String descripcion;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal costoEstimado;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, length = 20)
    private EstadoOrden estado;

    protected OrdenServicio() {
    }

    public OrdenServicio(Long idEquipo, LocalDate fechaIngreso, String descripcion, BigDecimal costoEstimado, EstadoOrden estado) {
        actualizar(idEquipo, fechaIngreso, descripcion, costoEstimado, estado);
    }

    public void actualizar(Long idEquipo, LocalDate fechaIngreso, String descripcion, BigDecimal costoEstimado, EstadoOrden estado) {
        this.idEquipo = idEquipo;
        this.fechaIngreso = fechaIngreso;
        this.descripcion = descripcion;
        this.costoEstimado = costoEstimado;
        this.estado = estado;
    }

    public void cambiarEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getCostoEstimado() {
        return costoEstimado;
    }

    public EstadoOrden getEstado() {
        return estado;
    }
}
