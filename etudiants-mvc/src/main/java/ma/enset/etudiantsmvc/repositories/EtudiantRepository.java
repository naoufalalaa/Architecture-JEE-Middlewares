package ma.enset.etudiantsmvc.repositories;

import ma.enset.etudiantsmvc.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Page<Etudiant> findByNom(String nom, Pageable pageable);
}
