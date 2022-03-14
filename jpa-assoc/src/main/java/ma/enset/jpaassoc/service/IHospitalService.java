package ma.enset.jpaassoc.service;

import ma.enset.jpaassoc.entities.Consultation;
import ma.enset.jpaassoc.entities.Medecin;
import ma.enset.jpaassoc.entities.Patient;
import ma.enset.jpaassoc.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}