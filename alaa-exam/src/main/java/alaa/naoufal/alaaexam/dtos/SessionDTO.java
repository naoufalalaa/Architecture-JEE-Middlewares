package alaa.naoufal.alaaexam.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SessionDTO {
    private Long id;
    private String nom;
    private String email;
    private String lienProfile;
    private List<ConferenceDTO> conferences;
    private ModeratorDTO moderator;
    private SalleDTO salle;
}
