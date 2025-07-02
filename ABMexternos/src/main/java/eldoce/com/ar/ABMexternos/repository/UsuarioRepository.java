package eldoce.com.ar.ABMexternos.repository;

import eldoce.com.ar.ABMexternos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consultas personalizadas si las necesitás
    Usuario findByDni(String dni);

    boolean existsByDni(String dni);

    @Query("""
    SELECT u FROM Usuario u
    WHERE (:nombre IS NULL OR LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
      AND (:apellido IS NULL OR LOWER(u.apellido) LIKE LOWER(CONCAT('%', :apellido, '%')))
      AND (:dni IS NULL OR u.dni LIKE CONCAT('%', :dni, '%'))
""")
    List<Usuario> buscarPorNombreApellidoDni(@Param("nombre") String nombre,
                                             @Param("apellido") String apellido,
                                             @Param("dni") String dni);




}
