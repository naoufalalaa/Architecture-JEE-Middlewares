package ma.enset.jpaap;

import ma.enset.jpaap.entities.Patient;
import ma.enset.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {
    @Autowired
    PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------------------------------------------------------------------------");

        patientRepository.save(new Patient(null,"Naoufal",new Date(), false,50));
        patientRepository.save(new Patient(null,"Hamza",new Date(), true,20));
        patientRepository.save(new Patient(null,"Zakaria",new Date(), false,150));
        List<Patient> patients = patientRepository.findAll();
        for (Patient p : patients) {
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
            System.out.println("###########################");
        }

        List<Patient> byMalade = patientRepository.findByMalade(false);
        patients = patientRepository.findByMalade(true);


    }
}
