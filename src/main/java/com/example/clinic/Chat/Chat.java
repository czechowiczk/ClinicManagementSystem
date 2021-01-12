package com.example.clinic.Chat;

import com.example.clinic.Employee.Doctor;
import com.example.clinic.Patient.Patient;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @Column(name = "id_chat")
    private Integer chatId;
    @Column(name = "id_patient")
    private Integer patientId;
    @Column(name = "id_doctor")
    private Integer doctorId;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_patient")
    Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_doctor")
    Doctor doctor;

    @OneToMany(mappedBy = "chat")
    private Set<ChatLine> lines;
}
