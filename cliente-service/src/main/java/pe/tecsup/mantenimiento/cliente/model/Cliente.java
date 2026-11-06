package pe.tecsup.mantenimiento.cliente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nombres;
    @Column(nullable = false, length = 80)
    private String apellidos;
    @Column(nullable = false, length = 150)
    private String correo;
    @Column(nullable = false, length = 25)
    private String telefono;

    protected Cliente() {
    }

    public Cliente(String nombres, String apellidos, String correo, String telefono) {
        actualizar(nombres, apellidos, correo, telefono);
    }

    public void actualizar(String nombres, String apellidos, String correo, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }
}
