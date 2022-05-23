package alaa.naoufal.alaaexam;

import alaa.naoufal.alaaexam.entities.*;
import alaa.naoufal.alaaexam.enums.Genre;
import alaa.naoufal.alaaexam.enums.StatutInvitation;
import alaa.naoufal.alaaexam.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class AlaaExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlaaExamApplication.class, args);
    }
    @Bean
    CommandLineRunner init(SalleRepository salleRepository,ConferenceRepository conferenceRepository, ParticipantRepository participantRepository, SessionRepository sessionRepository, InscriptionRepository inscriptionRepository) {
        return args -> {
            Salle salle1 = new Salle();
            salle1.setNom("Salle 1");
            Salle salle2 = new Salle();
            salle2.setNom("Salle 2");
            Salle salle3 = new Salle();
            salle3.setNom("Salle 3");
            Salle salle4 = new Salle();
            salle4.setNom("Salle 4");
            salleRepository.save(salle1);
            salleRepository.save(salle2);
            salleRepository.save(salle3);
            salleRepository.save(salle4);

            List<Moderator> moderators = new ArrayList<>();
            Stream.of("Naoufal","Alaa","Bastien").forEach(name -> {
                Moderator moderator = new Moderator();
                moderator.setNom(name);
                moderator.setEmail(name.toLowerCase()+"@gmail.com");
                moderator.setPhoto(name.toLowerCase()+".jpg");
                moderator.setSpecialite("Java");
                moderator.setGenre(Genre.MASCULIN);
                participantRepository.save(moderator);
                moderators.add(moderator);
            });

            List<Speaker> speakers = new ArrayList<>();
            Stream.of("Nasser","Mohammed","Nabil").forEach(
                    name -> {
                        Speaker speaker = new Speaker();
                        speaker.setNom(name);
                        speaker.setEmail(name.toLowerCase()+"@gmail.com");
                        speaker.setPhoto(name.toLowerCase()+".jpg");
                        speaker.setLienProfile("LinkedIn.com/in/"+name.toLowerCase());
                        speaker.setGenre(Genre.MASCULIN);
                        participantRepository.save(speaker);
                        speakers.add(speaker);
                    }
            );


            Session session1 = new Session();;
            session1.setNom("Java");
            session1.setSalle(salle1);
            session1.setModerator(moderators.get(0));
            sessionRepository.save(session1);

            Session session2 = new Session();;
            session2.setNom("Java");
            session2.setSalle(salle2);
            session2.setModerator(moderators.get(1));
            sessionRepository.save(session2);

            Session session3 = new Session();;
            session3.setNom("Java");
            session3.setSalle(salle3);
            session3.setModerator(moderators.get(2));
            sessionRepository.save(session3);

            Inscription inscription1 = new Inscription();
            inscription1.setDate(new Date());
            inscription1.setMontant(2019.2);
            inscription1.setStatut(StatutInvitation.ENCOURS);
            inscription1.setSession(session1);
            inscriptionRepository.save(inscription1);

            Inscription inscription2 = new Inscription();
            inscription2.setDate(new Date());
            inscription2.setMontant(319.2);
            inscription2.setStatut(StatutInvitation.ANNULEE);
            inscription2.setSession(session2);
            inscriptionRepository.save(inscription2);

            Inscription inscription3 = new Inscription();
            inscription3.setDate(new Date());
            inscription3.setMontant(209.2);
            inscription3.setStatut(StatutInvitation.VALIDEE);
            inscription3.setSession(session3);
            inscriptionRepository.save(inscription3);

            Conference conference1 = new Conference();
            conference1.setTitre("Java");
            conference1.setDate(new Date());
            conference1.setDescription("Java");
            conference1.setSession(session1);
            conference1.setHeureDebut("08:00");
            conference1.setHeureFin("10:00");
            conference1.setSpeaker(speakers.get(0));
            conferenceRepository.save(conference1);

            Conference conference2 = new Conference();
            conference2.setTitre("Java");
            conference2.setDate(new Date());
            conference2.setDescription("Java");
            conference2.setSession(session2);
            conference2.setHeureDebut("10:00");
            conference2.setHeureFin("12:00");
            conference2.setSpeaker(speakers.get(1));
            conferenceRepository.save(conference2);

            Conference conference3 = new Conference();
            conference3.setTitre("Java");
            conference3.setDate(new Date());
            conference3.setDescription("Java");
            conference3.setSession(session3);
            conference3.setHeureDebut("12:00");
            conference3.setHeureFin("14:00");
            conference3.setSpeaker(speakers.get(2));
            conferenceRepository.save(conference3);

            speakers.forEach(speaker -> {
                speaker.setConferences(conferenceRepository.findAll());
                participantRepository.save(speaker);
            });

            moderators.forEach(moderator -> {
                moderator.setSessions(sessionRepository.findAll());
                participantRepository.save(moderator);
            });
            List<Invite> invites = new ArrayList<>();
            Stream.of("Khalid","Moustapha","Salim").forEach(
                    name -> {
                        Invite invite = new Invite();
                        invite.setNom(name);
                        invite.setEmail(name.toLowerCase()+"@gmail.com");
                        invite.setPhoto(name.toLowerCase()+".jpg");
                        invite.setAffiliation("Leyton");
                        invite.setGenre(Genre.MASCULIN);
                        participantRepository.save(invite);
                        invites.add(invite);
                    }
            );

            invites.forEach(invite -> {
                invite.setInscriptions(inscriptionRepository.findAll());
                participantRepository.save(invite);
            });

            salle1.setSessions(sessionRepository.findAll());
            salleRepository.save(salle1);

            salle2.setSessions(sessionRepository.findAll());
            salleRepository.save(salle2);

            salle3.setSessions(sessionRepository.findAll());
            salleRepository.save(salle3);

        };
    }
}
