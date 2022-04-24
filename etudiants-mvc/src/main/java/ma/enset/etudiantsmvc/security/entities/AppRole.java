package ma.enset.etudiantsmvc.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // une table en base de données role
@Data // getters, setters, toString, hashCode, equals
@NoArgsConstructor // constructeur sans paramètre
@AllArgsConstructor // constructeur avec paramètre
public class AppRole {
    @Id // clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrémentation
    private Long roleID; // id du role
    @Column(unique = true) // unique
    private String roleName; // nom du role
    private String description; // description du role
}
