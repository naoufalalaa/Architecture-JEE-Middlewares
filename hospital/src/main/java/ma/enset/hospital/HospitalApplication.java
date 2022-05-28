package ma.enset.hospital;

import ma.enset.hospital.entities.Consultation;
import ma.enset.hospital.entities.Medecin;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.enums.TypeConsultation;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.Rendez_vousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }
    @Bean
    CommandLineRunner start(MedecinRepository medecinRepository,
                            PatientRepository patientRepository,
                            ConsultationRepository consultationRepository,
                            Rendez_vousRepository rendez_vousRepository) {
        return args -> {

            Stream.of("Naoufal", "ALAA", "Hamza").forEach(name -> {
                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setSpecialite("Cardiologue");
                medecinRepository.save(medecin);
            });
            medecinRepository.findAll().forEach(medecin -> {
                Patient patient = new Patient();
                patient.setNom(medecin.getNom());
                patient.setEmail(medecin.getNom().toLowerCase() + "@gmail.com");
                patient.setDateNaissance(new Date());
                patientRepository.save(patient);
            });
            patientRepository.findAll().forEach(patient -> {
                Consultation consultation = new Consultation();
                consultation.setId(UUID.randomUUID().toString());
                consultation.setDescription("Description");
                consultation.setTypeConsultation(TypeConsultation.DISTANCIELLE);
                consultation.setRendez_vous();
                consultationRepository.save(consultation);
            });
        };
    }
}
