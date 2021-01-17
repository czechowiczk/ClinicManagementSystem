package com.vaadin.tutorial.backend.entity;

import com.example.clinic.Patient.Sex;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "id_patient")
public class Patient extends User {

    private Sex sex;

    @OneToMany(mappedBy="patient")
    private Set<Disease> diseases;

    @OneToMany(mappedBy="patient")
    private Set<LaboratoryTest> tests;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;

    @OneToMany(mappedBy = "patient")
    private Set<Chat> chats;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    User user;


    public Patient() {
    }

    public Patient(String name, String surname, Long PESEL, int age, String password, Role role) {
        super(name, surname, PESEL, age, password, role);
    }

    //
//    @Override
//    public Integer getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(Integer id) {
//        this.id = id;
//    }
}
