package com.axa.softwareacademy.patient.repository;

import com.axa.softwareacademy.patient.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findPatientByGivenName(String givenName);

    Patient findPatientByFamilyName(String familyName);
}
