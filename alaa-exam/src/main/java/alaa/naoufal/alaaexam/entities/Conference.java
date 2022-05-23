package alaa.naoufal.alaaexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Conference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String heureDebut;
    private String heureFin;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;
    @ManyToOne(fetch = FetchType.LAZY)
    private Speaker speaker;
    @OneToMany(mappedBy = "conference")
    private List<Commentaire> commentaires;
}
