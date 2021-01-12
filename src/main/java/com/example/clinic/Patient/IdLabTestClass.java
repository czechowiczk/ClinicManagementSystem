package com.example.clinic.Patient;

import java.io.Serializable;

public class IdLabTestClass implements Serializable {
    private Integer patientId;

    private Integer testId;

    public IdLabTestClass() {
    }

    public IdLabTestClass(Integer patientId, Integer testId) {
        this.patientId = patientId;
        this.testId = testId;
    }
}

