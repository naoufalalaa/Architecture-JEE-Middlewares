package alaa.naoufal.alaaexam.entities;

import alaa.naoufal.alaaexam.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
// single table strategy
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 10)
@AllArgsConstructor @NoArgsConstructor
public class Participant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String photo;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @OneToMany(mappedBy = "participant")
    private List<Commentaire> commentaires;
}
