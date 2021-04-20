package com.axa.softwareacademy.patient.service;

import com.axa.softwareacademy.patient.domain.Patient;
import com.axa.softwareacademy.patient.repository.PatientRepository;
import com.axa.softwareacademy.patient.request.PatientSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient findPatientByGivenName(String givenName) {
        return patientRepository.findPatientByGivenName(givenName);
    }

    public Patient findPatientByFamilyName(String familyName) {
        return patientRepository.findPatientByFamilyName(familyName);
    }

    public Patient findById(Integer id) throws Exception {
        return patientRepository.findById(id).orElseThrow(()-> new Exception("404"));
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void update(Patient patient, Integer id) throws Exception {
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isEmpty()) {
            log.debug("Find user by id failed");
            throw new Exception("404");
        }
        patient.setId(id);
        patientRepository.save(patient);
    }

    public void save(Patient patient) {
        patientRepository.save((patient));
    }

    public void delete(Integer id) throws Exception {
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isEmpty()) {
            log.debug("Find user by id failed");
            throw new Exception("404");
        }
        patientRepository.deleteById(id);
    }

    private Patient convertToPatient(PatientSaveRequest patientSaveRequest) {
        Patient patient = new Patient();
        patient.setGivenName(patientSaveRequest.getGivenName());
        patient.setFamilyName(patientSaveRequest.getFamilyName());
        patient.setAddress(patientSaveRequest.getAddress());
        patient.setDob(patientSaveRequest.getDob());
        patient.setSex(patientSaveRequest.getSex());
        patient.setPhone(patientSaveRequest.getPhone());
        patient.setAge(patientSaveRequest.getAge());
        return patient;
    }
}
