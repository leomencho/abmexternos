package eldoce.com.ar.ABMexternos.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "programas")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idprograma;

    @Column(name = "nombre_corto", nullable = false, length = 50)
    private String nombreCorto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    // Relación con la tabla intermedia
    @OneToMany(mappedBy = "programa")
    private List<EstadoUsuarioPrograma> estadosDeUsuarios;

    // Getters y Setters

    public Long getIdprograma() {
        return idprograma;
    }

    public void setIdprograma(Long idprograma) {
        this.idprograma = idprograma;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
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

    public List<EstadoUsuarioPrograma> getEstadosDeUsuarios() {
        return estadosDeUsuarios;
    }

    public void setEstadosDeUsuarios(List<EstadoUsuarioPrograma> estadosDeUsuarios) {
        this.estadosDeUsuarios = estadosDeUsuarios;
    }
}
