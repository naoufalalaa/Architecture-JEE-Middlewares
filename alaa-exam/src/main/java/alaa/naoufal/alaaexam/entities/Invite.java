package alaa.naoufal.alaaexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@DiscriminatorValue("Invite")
@Data @NoArgsConstructor @AllArgsConstructor
public class Invite extends Participant{
    private String affiliation;
    @ManyToMany
    private List<Inscription> inscriptions;
}
