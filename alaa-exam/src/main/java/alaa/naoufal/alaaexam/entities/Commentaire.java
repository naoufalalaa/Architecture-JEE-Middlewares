package alaa.naoufal.alaaexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Commentaire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String contenu;
    private int nbrLikes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Participant participant;
    @ManyToOne(fetch = FetchType.LAZY)
    private Conference conference;
}
