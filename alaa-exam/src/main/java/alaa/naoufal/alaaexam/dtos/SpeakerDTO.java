package alaa.naoufal.alaaexam.dtos;

import lombok.Data;

@Data
public class SpeakerDTO extends ParticipantDTO {
    private String nom;
    private String email;
    private String affiliation;
}
