package com.example.clinic.Patient;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(IdLabTestClass.class)
@Entity
@Table(name = "laboratory_tests")
public class LaboratoryTest {


    @Column(name="id_patient", nullable = false)
    private Integer patientId;
    @Id
    @Column(name = "id_test")
    private Integer testId;
    @Column(name = "type")
    private String type;
    @Column(name = "date")
    private String date;
    @Column(name = "description")
    private String description;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_patient", insertable=false, updatable=false)
    private Patient patient;

    public LaboratoryTest() {
    }

    public LaboratoryTest(Integer patientId, Integer testId, String type, String date, String description, Patient patient) {
        this.patientId = patientId;
        this.testId = testId;
        this.type = type;
        this.date = date;
        this.description = description;
        this.patient = patient;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
