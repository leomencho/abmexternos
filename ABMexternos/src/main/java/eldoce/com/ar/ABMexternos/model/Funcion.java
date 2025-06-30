package eldoce.com.ar.ABMexternos.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "funciones")
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idfunciones;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    // Relación inversa con Usuario
    @ManyToMany(mappedBy = "funciones")
    private List<Usuario> usuarios;

    // Getters y Setters

    public Long getIdfunciones() {
        return idfunciones;
    }

    public void setIdfunciones(Long idfunciones) {
        this.idfunciones = idfunciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
