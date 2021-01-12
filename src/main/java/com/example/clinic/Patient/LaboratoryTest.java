package com.example.clinic.Patient;

import javax.persistence.*;

@Entity
@Table
public class LaboratoryTest {

    @Id
    @Column(name="patient_id", nullable = false)
    private Integer patientId;
    private String type;
    private String date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable=false, updatable=false)
    private Patient patient;

    public LaboratoryTest() {
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
