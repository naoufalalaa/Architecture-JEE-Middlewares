package ma.enset.jpasemaine5.web;

import ma.enset.jpasemaine5.entities.User;
import ma.enset.jpasemaine5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<User> users(){
        return userService.getUSERS();
    }
    @GetMapping("/users/{username}")
    public User userSearch(@PathVariable String username){
        return userService.findUserByUsername(username);
    }
}
