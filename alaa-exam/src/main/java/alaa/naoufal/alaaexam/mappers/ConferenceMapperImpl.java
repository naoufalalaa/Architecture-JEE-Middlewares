package alaa.naoufal.alaaexam.mappers;

import alaa.naoufal.alaaexam.dtos.*;
import alaa.naoufal.alaaexam.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ConferenceMapperImpl {
    public ParticipantDTO fromParticipant(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        BeanUtils.copyProperties(participant, participantDTO);
        return participantDTO;
    }

    public InviteDTO fromInvite(Invite participant) {
        InviteDTO inviteDTO = new InviteDTO();
        BeanUtils.copyProperties(participant, inviteDTO);
        inviteDTO.setType(participant.getClass().getSimpleName());
        return inviteDTO;
    }
    public ModeratorDTO fromModerator(Moderator participant) {
        ModeratorDTO moderatorDTO = new ModeratorDTO();
        BeanUtils.copyProperties(participant, moderatorDTO);
        moderatorDTO.setType(participant.getClass().getSimpleName());
        return moderatorDTO;
    }
    public SpeakerDTO fromSpeaker(Speaker participant) {
        SpeakerDTO speakerDTO = new SpeakerDTO();
        BeanUtils.copyProperties(participant, speakerDTO);
        speakerDTO.setType(participant.getClass().getSimpleName());
        return speakerDTO;
    }

    public Participant fromParticipantDTO(ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        BeanUtils.copyProperties(participantDTO, participant);
        return participant;
    }
    public Invite fromInviteDTO(InviteDTO inviteDTO) {
        Invite invite = new Invite();
        BeanUtils.copyProperties(inviteDTO, invite);
        return invite;
    }
    public Moderator fromModeratorDTO(ModeratorDTO moderatorDTO) {
        Moderator moderator = new Moderator();
        BeanUtils.copyProperties(moderatorDTO, moderator);
        return moderator;
    }
    public Speaker fromSpeakerDTO(SpeakerDTO speakerDTO) {
        Speaker speaker = new Speaker();
        BeanUtils.copyProperties(speakerDTO, speaker);
        return speaker;
    }

    public ConferenceDTO fromConference(Conference conference) {
        ConferenceDTO conferenceDTO = new ConferenceDTO();
        BeanUtils.copyProperties(conference, conferenceDTO);
        conferenceDTO.setSpeaker(fromSpeaker(conference.getSpeaker()));
        return conferenceDTO;
    }
    public Conference fromConferenceDTO(ConferenceDTO conferenceDTO) {
        Conference conference = new Conference();
        BeanUtils.copyProperties(conferenceDTO, conference);
        conference.setSpeaker(fromSpeakerDTO(conferenceDTO.getSpeaker()));
        return conference;
    }

    public SalleDTO fromSalle(Salle salle) {
        SalleDTO salleDTO = new SalleDTO();
        BeanUtils.copyProperties(salle, salleDTO);
        return salleDTO;
    }
    public Salle fromSalleDTO(SalleDTO salleDTO) {
        Salle salle = new Salle();
        BeanUtils.copyProperties(salleDTO, salle);
        return salle;
    }

    public SessionDTO fromSession(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        BeanUtils.copyProperties(session, sessionDTO);
        sessionDTO.setConferences(session.getConferences().stream().map(this::fromConference).collect(Collectors.toList()));
        sessionDTO.setModerator(fromModerator(session.getModerator()));
        sessionDTO.setSalle(fromSalle(session.getSalle()));
        return sessionDTO;
    }
    public Session fromSessionDTO(SessionDTO sessionDTO) {
        Session session = new Session();
        BeanUtils.copyProperties(sessionDTO, session);
        session.setConferences(sessionDTO.getConferences().stream().map(this::fromConferenceDTO).collect(Collectors.toList()));
        session.setModerator(fromModeratorDTO(sessionDTO.getModerator()));
        session.setSalle(fromSalleDTO(sessionDTO.getSalle()));
        return session;
    }




}
