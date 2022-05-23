package alaa.naoufal.alaaexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("Speaker")
@Data @NoArgsConstructor @AllArgsConstructor
public class Speaker extends Participant {
    private String lienProfile;
    @OneToMany
    private List<Conference> conferences;
}
