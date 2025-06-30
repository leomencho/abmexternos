package eldoce.com.ar.ABMexternos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seguros") // o el nombre real de la tabla
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreArchivo;    // Ej: "obra_social_2025.pdf"
    private String rutaArchivo;      // Ej: "/archivos/seguros/usuario123/..."

    @ManyToOne
    @JoinColumn(name = "id_usuario") // clave foránea
    private Usuario usuario;

    // Otros campos si aplican (tipo de seguro, fecha, etc.)

    // Getters y Setters
}
