package ma.enset.jpasemaine5.repositories;

import ma.enset.jpasemaine5.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String rolename);
}
