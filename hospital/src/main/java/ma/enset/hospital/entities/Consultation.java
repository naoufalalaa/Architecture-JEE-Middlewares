package ma.enset.hospital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.hospital.enums.TypeConsultation;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Consultation {
    @Id
    private String id;
    private String description;
    private String traitement_prescrit;
    @Enumerated(EnumType.STRING)
    private TypeConsultation typeConsultation;
    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Rendez_vous rendez_vous;
}
