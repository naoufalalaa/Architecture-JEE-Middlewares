package ma.enset.digitalbanking.security.service;

import ma.enset.digitalbanking.security.entities.AppRole;
import ma.enset.digitalbanking.security.entities.AppUser;

import java.util.List;

public interface SecurityService {

    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser( String username, String roleName);
    AppUser loadUserByUsername( String username);
    List<AppUser> listUsers();
    // login
    AppUser login(AppUser appUser);
    // logout
}
