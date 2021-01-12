package com.example.clinic.Patient;

import javax.persistence.*;
import java.time.LocalDate;

@IdClass(IdDiseaseClass.class)
@Entity
@Table(name = "disease_history")
public class Disease {
    @Id
    @Column(name = "id_disease")
    private Integer diseaseId;
    @Column(name="id_patient", nullable = false)
    private Integer patientId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private LocalDate date;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_patient", insertable=false, updatable=false)
    private Patient patient;


    public Disease() {
    }

    public Disease(Integer diseaseId, Integer patientId, String name, String description, LocalDate date, Patient patient) {
        this.diseaseId = diseaseId;
        this.patientId = patientId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
