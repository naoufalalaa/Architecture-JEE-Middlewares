package ma.enset.jpasemaine5.repositories;

import ma.enset.jpasemaine5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositories extends JpaRepository<User,String> {
    User findByUsername(String username);
    List<User> findAll();
}
