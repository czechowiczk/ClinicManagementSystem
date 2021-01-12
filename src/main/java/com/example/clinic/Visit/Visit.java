package com.example.clinic.Visit;

import com.example.clinic.Employee.Doctor;
import com.example.clinic.Patient.Patient;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @JoinColumn(name = "id_doctor")
    Doctor doctor;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_patient")
    Patient patient;

}
