package ma.enset.etudiantsmvc;

import ma.enset.etudiantsmvc.entities.Etudiant;
import ma.enset.etudiantsmvc.entities.Genre;
import ma.enset.etudiantsmvc.repositories.EtudiantRepository;
import ma.enset.etudiantsmvc.security.entities.AppRole;
import ma.enset.etudiantsmvc.security.entities.AppUser;
import ma.enset.etudiantsmvc.security.repositories.AppRoleRepository;
import ma.enset.etudiantsmvc.security.repositories.AppUserRepository;
import ma.enset.etudiantsmvc.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class EtudiantsMvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(EtudiantsMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner init(EtudiantRepository etudiantRepository){
        return args -> {
            etudiantRepository.save(new Etudiant(null, "Naoufal", "ALAA", "alaanaoufal@gmail.com", Genre.MASCULIN, new Date(), true));
            etudiantRepository.save(new Etudiant(null, "Majid", "Ramli", "ramli@gmail.com", Genre.MASCULIN, new Date(), false));
            etudiantRepository.save(new Etudiant(null, "Hamza", "Ait", "hamza@gmail.com", Genre.MASCULIN, new Date(), true));
            etudiantRepository.save(new Etudiant(null, "Khaoula", "elmajni", "khaoula@gmail.com", Genre.FEMININ, new Date(), true));
        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("naoufal","1620","1620");
            securityService.saveNewUser("yassine","0000","0000");
            securityService.saveNewUser("issam","1111","1111");

            securityService.saveNewRole("USER","standard access");
            securityService.saveNewRole("ADMIN","deep access");

            securityService.addRoleToUser("naoufal","ADMIN");
            securityService.addRoleToUser("naoufal","USER");
            securityService.addRoleToUser("issam","USER");
            securityService.addRoleToUser("yassine","USER");

        };
    }

    @Bean
    PasswordEncoder AppPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
