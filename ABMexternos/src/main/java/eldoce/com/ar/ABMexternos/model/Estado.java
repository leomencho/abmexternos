package eldoce.com.ar.ABMexternos.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idestado;

    @Column(nullable = false, length = 50)
    private String tipoestado;

    // Relación con la tabla intermedia
    @OneToMany(mappedBy = "estado")
    private List<EstadoUsuarioPrograma> usuariosPorEstado;

    // Getters y Setters

    public Long getIdestado() {
        return idestado;
    }

    public void setIdestado(Long idestado) {
        this.idestado = idestado;
    }

    public String getTipoestado() {
        return tipoestado;
    }

    public void setTipoestado(String tipoestado) {
        this.tipoestado = tipoestado;
    }

    public List<EstadoUsuarioPrograma> getUsuariosPorEstado() {
        return usuariosPorEstado;
    }

    public void setUsuariosPorEstado(List<EstadoUsuarioPrograma> usuariosPorEstado) {
        this.usuariosPorEstado = usuariosPorEstado;
    }
}
