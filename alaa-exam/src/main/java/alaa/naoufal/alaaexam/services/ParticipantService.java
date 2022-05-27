package alaa.naoufal.alaaexam.services;

import alaa.naoufal.alaaexam.dtos.ParticipantDTO;
import alaa.naoufal.alaaexam.entities.Participant;

import java.util.List;

public interface ParticipantService {
    public List<ParticipantDTO> getAllParticipants();
}
