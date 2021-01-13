package com.example.clinic.Patient;

import java.io.Serializable;

public class IdPatientClass implements Serializable {
    private Integer patientId;
    private Integer userId;

    public IdPatientClass() {
    }

    public IdPatientClass(Integer patientId, Integer userId) {
        this.patientId = patientId;
        this.userId = userId;
    }
}
