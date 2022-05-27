package alaa.naoufal.alaaexam.dtos;

import alaa.naoufal.alaaexam.entities.Commentaire;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ConferenceDTO {
    private Long id;
    private String titre;
    private Date date;
    private String heureDebut;
    private String heureFin;
    private String description;
    private SpeakerDTO speaker;
    private SessionDTO session;
    private List<CommentaireDTO> commentaireDTOs;
}
