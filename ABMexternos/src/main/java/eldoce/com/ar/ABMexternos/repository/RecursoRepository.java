package eldoce.com.ar.ABMexternos.repository;

import eldoce.com.ar.ABMexternos.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    Optional<Recurso> findByNombre(String nombre);
}
