package com.clinic.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visit")
    private Integer visitId;
    @Column(name = "id_doctor")
    private Integer doctorId;
    @Column(name = "id_patient")
    private Integer patientId;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "description")
    private String description;
    @Column(name = "time")
    private LocalTime time;

    public Visit() {
    }

    public Visit(LocalDate date, String purpose, String description, LocalTime time) {
        this.date = date;
        this.purpose = purpose;
        this.description = description;
        this.time = time;
    }

    public Visit(Integer doctorId, Integer patientId, LocalDate date, String purpose, String description, LocalTime time) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.purpose = purpose;
        this.description = description;
        this.time = time;
    }

    @ManyToOne
    @JoinColumn(name = "id_doctor", insertable=false, updatable=false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "id_patient", insertable=false, updatable=false)
    private Patient patient;

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
