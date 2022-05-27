package alaa.naoufal.alaaexam.dtos;

import lombok.Data;

@Data
public class ModeratorDTO extends ParticipantDTO {
    private Long id;
    private String nom;
    private String email;
    private String speciality;
}
