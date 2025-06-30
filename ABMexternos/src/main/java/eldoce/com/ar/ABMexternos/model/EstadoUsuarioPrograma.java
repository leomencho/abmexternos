package eldoce.com.ar.ABMexternos.model;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario_programa_estado")
public class EstadoUsuarioPrograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_programa")
    private Programa programa;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    private String observaciones;

    private String fechaDesde;
    private String fechaHasta;

    // Getters y Setters
}

