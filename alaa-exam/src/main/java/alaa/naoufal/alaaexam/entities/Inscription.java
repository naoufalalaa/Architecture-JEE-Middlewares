package alaa.naoufal.alaaexam.entities;

import alaa.naoufal.alaaexam.enums.StatutInvitation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Inscription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private StatutInvitation statut;
    private double montant;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Invite> invites;
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;
}
