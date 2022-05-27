package alaa.naoufal.alaaexam.web;

import alaa.naoufal.alaaexam.dtos.ParticipantDTO;
import alaa.naoufal.alaaexam.mappers.ConferenceMapperImpl;
import alaa.naoufal.alaaexam.services.ParticipantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ParticipantRestController {
    private ParticipantService participantService;

    @GetMapping("/participants")
    public List<ParticipantDTO> getBankAccount() {
        return participantService.getAllParticipants();
    }

}
