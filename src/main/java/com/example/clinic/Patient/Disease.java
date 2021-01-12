package com.example.clinic.Patient;

import javax.persistence.*;

@Entity
@Table(name = "disease_history")
public class Disease {
    @Id
    private String name;
    private String date;
    private String description;
    @Column(name="patient_id", nullable = false)
    private Integer patientId;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable=false, updatable=false)
    private Patient patient;


    public Disease() {
    }

    public Disease(String name, String date, String description, Integer patientId, Patient patient) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.patientId = patientId;
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
