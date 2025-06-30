package eldoce.com.ar.ABMexternos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarios;

    private String nombre;
    private String apellido;
    private String dni;
    private String cuil;

    @Column(name = "fnac")
    private LocalDate fechaNacimiento;

    private String mail;
    private String foto;
    private String pass;
    // 👉 NUEVO: relación con tabla intermedia EstadoUsuarioPrograma
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<EstadoUsuarioPrograma> estadoPrograma;

    // 👉 Funcion (N:M)
    @ManyToMany
    @JoinTable(
            name = "Usuarios_has_Funciones",
            joinColumns = @JoinColumn(name = "idUsuarios"),
            inverseJoinColumns = @JoinColumn(name = "idFunciones")
    )
    private List<Funcion> funciones;

    // 👉 Reporta (N:M con self)
    @ManyToMany
    @JoinTable(
            name = "Usuarios_has_Reporta",
            joinColumns = @JoinColumn(name = "idUsuarios"),
            inverseJoinColumns = @JoinColumn(name = "idReporta")
    )
    private List<Usuario> reporta;

    // 👉 Perfil (N:M)
    @ManyToMany
    @JoinTable(
            name = "Usuarios_has_Perfiles",
            joinColumns = @JoinColumn(name = "idUsuarios"),
            inverseJoinColumns = @JoinColumn(name = "idPerfil")
    )
    private List<Perfil> perfiles;

    // 👉 Seguro (1:N)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Seguro> seguros;

    @ManyToMany
    @JoinTable(
            name = "usuarios_has_recursos",
            joinColumns = @JoinColumn(name = "idUsuarios"),
            inverseJoinColumns = @JoinColumn(name = "idRecurso")
    )
    private List<Recurso> recursos;

}