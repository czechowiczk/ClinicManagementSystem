package com.example.clinic.Patient;

import java.io.Serializable;

public class IdDiseaseClass implements Serializable {

    private Integer diseaseId;

    private Integer patientId;

    public IdDiseaseClass(Integer diseaseId, Integer patientId) {
        this.patientId = patientId;
        this.diseaseId = diseaseId;
    }

    private IdDiseaseClass() {
    }
}