package ma.enset.etudiantsmvc.security.service;

import ma.enset.etudiantsmvc.security.entities.AppRole;
import ma.enset.etudiantsmvc.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username,String password , String rePassword); // ajouter un user
    AppRole saveNewRole(String roleName,String description); // ajouter un role
    void addRoleToUser(String username,String roleName);   // ajouter un role a un user
    void removeRoleFromUser(String username,String roleName); // supprimer un role a un user
    AppUser loadUserByUserName(String username); // charger un user
}
