package alaa.naoufal.alaaexam.security.repositories;

import alaa.naoufal.alaaexam.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
