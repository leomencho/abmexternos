package eldoce.com.ar.ABMexternos.repository;

import eldoce.com.ar.ABMexternos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consultas personalizadas si las necesitás
    Usuario findByDni(String dni);

    boolean existsByDni(String dni);

    // podés agregar más métodos como:
    // List<Usuario> findByApellidoContainingIgnoreCase(String apellido);
}
