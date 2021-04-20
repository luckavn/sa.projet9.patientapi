package com.axa.softwareacademy.patient.controller;

import com.axa.softwareacademy.patient.domain.Patient;
import com.axa.softwareacademy.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("patients")
@RequiredArgsConstructor
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    /**
     * This endpoint is aimed to find patient information
     * @param givenName is the first name of the patient
     * @return the patient
     */
    @GetMapping("given-name")
    public Patient getPatientByGivenName(@RequestParam String givenName) {
        return patientService.findPatientByGivenName(givenName);
    }

    /**
     * This endpoint is aimed to find patient information
     * @param familyName is the last name of the patient
     * @return the patient
     */
    @GetMapping("family-name")
    public Patient getPatientByFamilyName(@RequestParam String familyName) {
        return patientService.findPatientByFamilyName(familyName);
    }

    /**
     * This endpoint is aimed to find patient information
     * @param id is the unique id number of the patient
     * @return the patient
     */
    @GetMapping("id")
    public Patient getPatientById(@RequestParam Integer id) throws Exception {
        Patient patient = patientService.findById(id);
        log.debug(patient.getAddress());
        return patient;
    }

    /**
     * This endpoint is aimed to find a list of patient information
     * @return a list of all patients
     */
    @GetMapping
    public List<Patient> patientList() {
        log.debug("Start finding all patients");
        return patientService.findAll();
    }

    /**
     * This endpoint is aimed to add a patient to database
     * @return a 201 created if ok
     */
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {
        log.debug("Start saving patient");
        patientService.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
    }

    /**
     * This endpoint is aimed to modify a patient present in database
     * @return a 201 created if ok
     */
    @PutMapping
    public ResponseEntity<Patient> modifyPatient(@RequestBody @Valid Patient patient, @RequestParam Integer id) throws Exception {
        patientService.update(patient, id);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    /**
     * This endpoint is aimed to delete a patient present in database
     * @param id is the unique id number of the patient
     * @throws Exception when patient is not found in database
     */
    @DeleteMapping
    public void deletePatient(@RequestParam String id) throws Exception {
        int id1 = Integer.parseInt(id);
        patientService.delete(id1);
    }
}
