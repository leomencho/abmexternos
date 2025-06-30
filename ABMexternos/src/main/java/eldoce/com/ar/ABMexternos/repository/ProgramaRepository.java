package eldoce.com.ar.ABMexternos.repository;

import eldoce.com.ar.ABMexternos.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    boolean existsByNombreCorto(String nombreCorto); // ejemplo extra
}
