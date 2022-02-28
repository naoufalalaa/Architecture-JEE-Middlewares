package ma.enset.jpaap.repositories;

import ma.enset.jpaap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    public List<Patient> findByMalade(Boolean m);
    Page<Patient> findByMalade(boolean m , Pageable pageable);
    List<Patient> findByMaladeAndScoreLessThan(boolean m , int score);
    List<Patient> findByMaladeIsFalseAndScoreLessThan(int score);
}
