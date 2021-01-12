package com.example.clinic.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("patient")
public class PatientController {
    @Qualifier("PatientService")
    private final PatientService patientService;
    @Qualifier("DiseaseService")
    private final DiseaseService diseaseService;
    @Qualifier("LaboratoryTest")
    private final LaboratoryTestService laboratoryTestService;

    @Autowired
    public PatientController(PatientService patientService, DiseaseService diseaseService, LaboratoryTestService laboratoryTestService) {
        this.patientService = patientService;
        this.diseaseService = diseaseService;
        this.laboratoryTestService = laboratoryTestService;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/diseases")
    public List<Disease> getDiseases() {
        return diseaseService.getDiseases();
    }

    @GetMapping("/tests")
    public List<LaboratoryTest> getTests() {
        return laboratoryTestService.getTests();
    }


}
