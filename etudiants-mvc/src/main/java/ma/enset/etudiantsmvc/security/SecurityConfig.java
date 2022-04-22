package ma.enset.etudiantsmvc.security;
import lombok.AllArgsConstructor;
import ma.enset.etudiantsmvc.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration // cette annotation permet de configurer le security
@EnableWebSecurity // permet de configurer le security
@AllArgsConstructor // pour injecter les dépendances
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsServiceImpl; //injection de dependance

    @Override // pour configurer le UserDetailsService
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl); // pour configurer le UserDetailsService
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // pour configurer les routes
        http.formLogin().loginPage("/login").permitAll(); // pour configurer la route du login
        http.logout().logoutSuccessHandler((req, res, auth) -> res.sendRedirect("/login")); // pour configurer la route du logout et rediriger vers la route du login
        http.authorizeRequests().antMatchers("/").permitAll();// pour configurer la route de l'accueil et autoriser tout le monde
        http.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ADMIN"); // pour configurer la route de l'admin et autoriser uniquement les admins
        http.authorizeRequests().antMatchers("/user/**").hasAnyAuthority("USER"); // pour configurer la route de l'user et autoriser uniquement les users
        http.authorizeRequests().antMatchers("/webjars/**").permitAll(); // pour configurer la route de webjars et autoriser tout le monde
        http.authorizeRequests().antMatchers("/images/**").permitAll(); // pour configurer la route des images et autoriser tout le monde
        http.authorizeRequests().antMatchers("/static/**").permitAll(); // pour configurer la route des fichiers statiques et autoriser tout le monde
        http.authorizeRequests().anyRequest().authenticated(); // pour configurer toutes les routes et autoriser tout le monde
        http.exceptionHandling().accessDeniedPage("/403"); // pour configurer la route de l'erreur 403
    }

}