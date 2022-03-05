package ma.enset.jpaap;

import ma.enset.jpaap.entities.Patient;
import ma.enset.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
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

        //Filling the database with random data
        patientRepository.save(new Patient(null,"Naoufal",new Date(), false,50));
        patientRepository.save(new Patient(null,"Hamza",new Date(), true,20));
        patientRepository.save(new Patient(null,"Zakaria",new Date(), false,150));

        // fetching all Patient data
        List<Patient> patients = patientRepository.findAll();

        // displaying ALL
        for (Patient p : patients) {
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
            System.out.println("###########################");
        }
        // Fetching ALL MALADE = FALSE
        List<Patient> byMalade = patientRepository.findByMalade(false);
        System.out.println("------ Patients PAS Malades ------");
        for (Patient p : byMalade) {
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
            System.out.println("###########################");
        }
        // Fetching ALL MALADE = FALSE
        byMalade = patientRepository.findByMalade(true);
        System.out.println("------ Patients Malades ------");
        for (Patient p : byMalade) {
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
            System.out.println("###########################");
        }


        // Using @QUERY
        List<Patient> byDate = patientRepository.FindPatients(new Date(2020,3,1),new Date(2022,3,1),"Naoufal");
        System.out.println("------ Patients between dates ------");
        for (Patient p : byDate) {
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
            System.out.println("###########################");
        }

    }
}
