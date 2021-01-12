package com.example.clinic.Visit;

import javax.persistence.Column;
import java.io.Serializable;

public class IdVisitClass implements Serializable {

    private Integer visitId;
    private Integer doctorId;
    private Integer patientId;

    public IdVisitClass() {
    }

    public IdVisitClass(Integer visitId, Integer doctorId, Integer patientId) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }
}
