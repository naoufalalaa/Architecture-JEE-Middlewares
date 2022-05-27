package alaa.naoufal.alaaexam.services;

import alaa.naoufal.alaaexam.dtos.ParticipantDTO;
import alaa.naoufal.alaaexam.entities.Participant;
import alaa.naoufal.alaaexam.repositories.ParticipantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Transactional
@Service
@AllArgsConstructor
@Slf4j //pour logger les msgs
public class ParticipantServiceImpl implements ParticipantService {
    ParticipantRepository participantRepository;
    @Override
    public List<ParticipantDTO> getAllParticipants() {
        return participantRepository.findAll().stream().map(
                participant -> {
                    ParticipantDTO participantDTO = new ParticipantDTO();
                    BeanUtils.copyProperties(participant, participantDTO);
                    return participantDTO;
                }
        ).collect(Collectors.toList());
    }

}
