package eldoce.com.ar.ABMexternos.model;

import eldoce.com.ar.ABMexternos.validation.MinEdad;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String telefono;

    @Column(length = 500)
    private String comentario;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "La fecha debe ser en el pasado")
    @MinEdad(18) // <-- esta es personalizada, la creamos abajo
    @Column(name = "fnac")
    private LocalDate fechaNacimiento;

    private String mail;
    private String foto;
    private String pass;


    @ManyToMany
    @JoinTable(
            name = "Usuarios_has_Programas",
            joinColumns = @JoinColumn(name = "idUsuarios"),
            inverseJoinColumns = @JoinColumn(name = "idPrograma")
    )
    private List<Programa> programas;
    // 👉 NUEVO: relación con tabla intermedia EstadoUsuarioPrograma


    @ManyToOne
    @JoinColumn(name = "estado_id") // este será el nombre de la FK en la tabla usuarios
    private Estado estado;


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


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Long idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programa> programas) {
        this.programas = programas;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

    public List<Usuario> getReporta() {
        return reporta;
    }

    public void setReporta(List<Usuario> reporta) {
        this.reporta = reporta;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public List<Seguro> getSeguros() {
        return seguros;
    }

    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuarios=" + idUsuarios +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", cuil='" + cuil + '\'' +
                ", telefono='" + telefono + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", mail='" + mail + '\'' +
                ", foto='" + foto + '\'' +
                ", pass='" + pass + '\'' +
                ", programas=" + programas +
                ", estado=" + estado +
                ", funciones=" + funciones +
                ", reporta=" + reporta +
                ", perfiles=" + perfiles +
                ", seguros=" + seguros +
                ", recursos=" + recursos +
                '}';
    }
}
