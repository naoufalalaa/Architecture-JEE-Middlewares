package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args ->{
            patientRepository.save(
                    new Patient(null,"Naoufal",new Date(),false,12)
            );
            patientRepository.save(
                    new Patient(null,"Abdelmajid",new Date(),false,19)
            );
            patientRepository.save(
                    new Patient(null,"Zakaria",new Date(),true,2)
            );
        };
    }
}
