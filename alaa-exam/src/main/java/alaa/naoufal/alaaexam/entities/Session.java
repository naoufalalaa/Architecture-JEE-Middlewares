package alaa.naoufal.alaaexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToOne(fetch = FetchType.LAZY)
    private Salle salle;
    @ManyToOne(fetch = FetchType.LAZY)
    private Moderator moderator;
    @OneToMany(mappedBy = "session")
    private List<Conference> conferences;
    @OneToMany(mappedBy = "session")
    private List<Inscription> inscriptions;
}
