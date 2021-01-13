package com.example.clinic.Visit;

import com.example.clinic.Employee.Doctor;
import com.example.clinic.Patient.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@IdClass(IdVisitClass.class)
@Entity
@Table(name = "visits")
public class Visit {

    @Id
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

    @Id
    @ManyToOne
    @JoinColumn(name = "id_doctor", insertable=false, updatable=false)
    private Doctor doctor;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_patient", insertable=false, updatable=false)
    private Patient patient;

}
