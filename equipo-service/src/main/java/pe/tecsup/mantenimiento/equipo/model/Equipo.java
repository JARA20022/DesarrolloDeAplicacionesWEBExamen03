package pe.tecsup.mantenimiento.equipo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String codigo;
    @Column(nullable = false, length = 60)
    private String tipo;
    @Column(nullable = false, length = 60)
    private String marca;
    @Column(nullable = false, length = 80)
    private String modelo;
    @Column(nullable = false)
    private Long idCliente;

    protected Equipo() {
    }

    public Equipo(String codigo, String tipo, String marca, String modelo, Long idCliente) {
        actualizar(codigo, tipo, marca, modelo, idCliente);
    }

    public void actualizar(String codigo, String tipo, String marca, String modelo, Long idCliente) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Long getIdCliente() {
        return idCliente;
    }
}
