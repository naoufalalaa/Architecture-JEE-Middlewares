package ma.enset.etudiantsmvc.repositories;

import ma.enset.etudiantsmvc.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Page<Etudiant> findByNomContains(String nom, Pageable pageable);
    Etudiant findById(int ID);
}
