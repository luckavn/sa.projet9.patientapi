
package com.axa.softwareacademy.patient.controller;

import com.axa.softwareacademy.patient.domain.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class PatientControllerTest {
    private Patient patient = new Patient(1, "Doe", "John", "1 boulevard rd", new Date(1990 - 02 - 10), "M", "01010101010", 25);
    private Patient modifyPatient = new Patient(1, "Dooe", "John", "1 boulevard rd", new Date(1990 - 02 - 10), "M", "01010101010", 25);

    @Autowired
    MockMvc mockmvc;

    @BeforeEach
    void init() throws Exception {
        addPatient();
    }

    @Test
    public void getPatientByGivenName() throws Exception {
        this.mockmvc.perform(get("/patients/given-name")
                .param("givenName", "John"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getPatientByFamilyName() throws Exception {
        this.mockmvc.perform(get("/patients/family-name")
                .param("familyName", "Doe"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllPatients() throws Exception {
        this.mockmvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addPatient() throws Exception {
        this.mockmvc.perform(post("/patients")
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modifyPatient() throws Exception {
        addPatient();
        this.mockmvc.perform(put("/patients")
                .param("id","1")
                .content(asJsonString(modifyPatient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deletePatient() throws Exception {
        addPatient();
        this.mockmvc.perform(delete("/patients")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
