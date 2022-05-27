package alaa.naoufal.alaaexam.security;
import alaa.naoufal.alaaexam.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
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
        http.authorizeRequests().anyRequest().permitAll(); // pour toutes les routes
    }

}