package com.example.clinic.Patient;

import com.example.clinic.Chat.Chat;
import com.example.clinic.User.*;
import com.example.clinic.Visit.Visit;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "patients")
@Inheritance(strategy = InheritanceType.JOINED)
public class Patient extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;
    @Column(name = "id_patient", nullable = false)
    private Integer patientId;
    private String sex;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @OneToMany(mappedBy = "patient")
    private Set<Disease> diseases;

    @OneToMany(mappedBy = "patient")
    private Set<LaboratoryTest> tests;

    @OneToMany(mappedBy = "patient")
    private Set<Chat> chats;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;


    public Patient() {
    }

    public Patient(Integer patientId) {
        super();
        this.patientId = patientId;
    }

    public Patient(Integer id,
                   String name,
                   String surname,
                   Long PESEL,
                   int age,
                   String password,
                   Integer id1,
                   String sex) {
        super(id, name, surname, PESEL, age, password);
        this.id = id1;
        this.sex = sex;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
