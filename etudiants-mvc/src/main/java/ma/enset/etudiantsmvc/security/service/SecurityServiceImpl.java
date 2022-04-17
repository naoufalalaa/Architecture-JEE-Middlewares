package ma.enset.etudiantsmvc.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.etudiantsmvc.security.entities.AppRole;
import ma.enset.etudiantsmvc.security.entities.AppUser;
import ma.enset.etudiantsmvc.security.repositories.AppRoleRepository;
import ma.enset.etudiantsmvc.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service //@Service pour que cette classe soit un service
@Slf4j // log
@AllArgsConstructor // injection de dependance
@Transactional // transaction
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder appPasswordEncoder;


    @Override //@Transactional
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Password doesn't match");
        String hashedPWD = appPasswordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserID(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setActive(true);
        appUser.setPassword(hashedPWD);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override //@Transactional
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole != null) throw new RuntimeException("Role "+roleName+" already exists");
        appRole=new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override //@Transactional
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User "+username+" not found !");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("Role "+roleName+" not found !");
        appUser.getAppRoles().add(appRole);
        appUserRepository.save(appUser);
    }

    @Override //@Transactional
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User "+username+" not found !");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("Role "+roleName+" not found !");
        appUser.getAppRoles().remove(appRole);
        appUserRepository.save(appUser);
    }

    @Override //@Transactional
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }


}
