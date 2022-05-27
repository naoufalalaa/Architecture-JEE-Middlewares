package alaa.naoufal.alaaexam.security.service;

import alaa.naoufal.alaaexam.security.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired //injection de dépendance
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = securityService.loadUserByUserName(username);
        //en utilisant l'API des streams
        Collection<GrantedAuthority> authorities1 = appUser //on récupère la liste des roles
                .getAppRoles() //on récupère la liste des roles
                .stream() //on transforme la liste en stream
                .map(role-> new SimpleGrantedAuthority(role.getRoleName())) //on transforme chaque role en objet GrantedAuthority
                .collect(Collectors.toList());//on transforme le stream en liste

        User user = new User(appUser.getUsername(),appUser.getPassword(),authorities1);//on crée un objet User
        return user;
    }
}
