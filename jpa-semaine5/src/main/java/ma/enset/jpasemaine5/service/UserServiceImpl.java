package ma.enset.jpasemaine5.service;

import lombok.AllArgsConstructor;
import ma.enset.jpasemaine5.entities.Role;
import ma.enset.jpasemaine5.entities.User;
import ma.enset.jpasemaine5.repositories.RoleRepository;
import ma.enset.jpasemaine5.repositories.UserRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepositories userRepositories;
    private RoleRepository roleRepository;


    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepositories.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepositories.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String rolename) {
        return roleRepository.findByRoleName(rolename);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = findUserByUsername(username);
        Role role = findRoleByRoleName(rolename);
        if (user.getRoles() != null && role.getUsers() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepositories.findByUsername(username);
        if(user!=null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new RuntimeException("Bad Credentials");
    }

    @Override
    public List<User> getUSERS() {
        return userRepositories.findAll();
    }

}
