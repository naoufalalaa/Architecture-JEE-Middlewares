package ma.enset.jpasemaine5;

import ma.enset.jpasemaine5.entities.Role;
import ma.enset.jpasemaine5.entities.User;
import ma.enset.jpasemaine5.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaSemaine5Application {

    public static void main(String[] args) {
        SpringApplication.run(JpaSemaine5Application.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            User u = new User();
            u.setUsername("naoufal_alaa");
            u.setPassword("123456");
            userService.addNewUser(u);
            User u2 = new User();
            u2.setUsername("fairy_lhs");
            u2.setPassword("123456");
            userService.addNewUser(u2);
            User u3 = new User();
            u3.setUsername("admin");
            u3.setPassword("123456");
            userService.addNewUser(u3);

            Stream.of("STUDENT","USER","ADMIN").forEach(r->{
                Role role = new Role();
                role.setRoleName(r);
                userService.addNewRole(role);
            });

            userService.addRoleToUser("naoufal_alaa","STUDENT");
            userService.addRoleToUser("naoufal_alaa","USER");
            userService.addRoleToUser("fairy_lhs","USER");
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("admin","ADMIN");

            try{
                User user= userService.authenticate("naoufal_alaa","123456");

                System.out.println("-------");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());

                user.getRoles().forEach(role -> {
                    System.out.println("Role -> "+role);
                });
                System.out.println("-------");
            }catch (Exception e){
                e.printStackTrace();
            }
        };
    }
}
