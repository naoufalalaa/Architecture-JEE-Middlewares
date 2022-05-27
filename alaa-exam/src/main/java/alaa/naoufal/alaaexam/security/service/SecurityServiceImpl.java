package alaa.naoufal.alaaexam.security.service;

import alaa.naoufal.alaaexam.security.entities.AppRole;
import alaa.naoufal.alaaexam.security.entities.AppUser;
import alaa.naoufal.alaaexam.security.repositories.AppRoleRepository;
import alaa.naoufal.alaaexam.security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service //@Service pour que cette classe soit un service
@Slf4j // log
@AllArgsConstructor // injection de dependance
@Transactional // transaction
public class SecurityServiceImpl implements SecurityService { // implemente SecurityService
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder appPasswordEncoder;


    @Override //@Transactional
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Password doesn't match"); // si les mots de passe ne sont pas les mêmes
        String hashedPWD = appPasswordEncoder.encode(password); // encode le mot de passe
        AppUser appUser = new AppUser(); // créer un nouvel utilisateur
        appUser.setUserID(UUID.randomUUID().toString()); // générer un id
        appUser.setUsername(username); // nom d'utilisateur
        appUser.setActive(true); // actif
        appUser.setPassword(hashedPWD); // mot de passe
        AppUser savedAppUser = appUserRepository.save(appUser); // sauvegarder l'utilisateur
        return savedAppUser; // retourner l'utilisateur
    }

    @Override //@Transactional
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName); // rechercher le role
        if(appRole != null) throw new RuntimeException("Role "+roleName+" already exists"); // si le role existe déjà
        appRole=new AppRole(); // créer un nouveau role
        appRole.setRoleName(roleName); // nom du role
        appRole.setDescription(description);// description du role
        AppRole savedAppRole = appRoleRepository.save(appRole); // sauvegarder le role
        return savedAppRole;// retourner le role
    }

    @Override //@Transactional
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);// rechercher l'utilisateur
        if(appUser==null) throw new RuntimeException("User "+username+" not found !");// si l'utilisateur n'existe pas
        AppRole appRole = appRoleRepository.findByRoleName(roleName);// rechercher le role
        if(appRole==null) throw new RuntimeException("Role "+roleName+" not found !");// si le role n'existe pas
        appUser.getAppRoles().add(appRole);// ajouter le role à l'utilisateur
        appUserRepository.save(appUser);// sauvegarder l'utilisateur
    }

    @Override //@Transactional
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);// rechercher l'utilisateur
        if(appUser==null) throw new RuntimeException("User "+username+" not found !");// si l'utilisateur n'existe pas
        AppRole appRole = appRoleRepository.findByRoleName(roleName);// rechercher le role
        if(appRole==null) throw new RuntimeException("Role "+roleName+" not found !");/// si le role n'existe pas
        appUser.getAppRoles().remove(appRole);// supprimer le role de l'utilisateur
        appUserRepository.save(appUser);// sauvegarder l'utilisateur
    }

    @Override //@Transactional
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }


}
