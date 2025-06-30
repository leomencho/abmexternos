package eldoce.com.ar.ABMexternos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Ej: "Internet", "Correo", "Acceso VPN"

    @ManyToMany(mappedBy = "recursos")
    private List<Usuario> usuarios;


// Getters y Setters...
}