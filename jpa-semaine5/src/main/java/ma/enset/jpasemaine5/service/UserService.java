package ma.enset.jpasemaine5.service;

import ma.enset.jpasemaine5.entities.Role;
import ma.enset.jpasemaine5.entities.User;

import java.util.List;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRoleName(String rolename);
    void addRoleToUser(String username, String rolename);
    User authenticate(String username,String password);

    List<User> getUSERS();

}
