package alaa.naoufal.alaaexam.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("Moderator")
@Data @NoArgsConstructor @AllArgsConstructor
public class Moderator extends Participant {
    private String specialite;
    @OneToMany(mappedBy = "moderator")
    private List<Session> sessions;
}
