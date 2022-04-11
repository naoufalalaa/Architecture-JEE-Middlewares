package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import ma.enset.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("naoufal","1620","1620");
            securityService.saveNewUser("yassine","0000","0000");
            securityService.saveNewUser("mouad","1111","1111");

            securityService.saveNewRole("USER","standard access");
            securityService.saveNewRole("ADMIN","deep access");

            securityService.addRoleToUser("naoufal","ADMIN");
            securityService.addRoleToUser("mouad","USER");
            securityService.addRoleToUser("yassine","USER");


        };
    }

    @Bean
    PasswordEncoder AppPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
