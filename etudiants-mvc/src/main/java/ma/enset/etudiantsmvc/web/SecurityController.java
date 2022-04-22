package ma.enset.etudiantsmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {// @Controller

    @GetMapping("/403")
    public String notAuthorized() {
        return "403"; // retourne la vue 403.html
    }

    @GetMapping("/login")
    public String loginPage(){ // @GetMapping // @RequestMapping(value = "/login", method = RequestMethod.GET)
        return "login"; // retourne la vue login.html
    }
}