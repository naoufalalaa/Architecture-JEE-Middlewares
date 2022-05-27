package alaa.naoufal.alaaexam.dtos;

import alaa.naoufal.alaaexam.enums.StatutInvitation;
import lombok.Data;

import java.util.Date;

@Data
public class InscriptionDTO {
    private Long id;
    private Date date;
    private StatutInvitation statut;
    private double montant;
}
