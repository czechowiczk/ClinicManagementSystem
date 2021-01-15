package com.example.clinic.Patient;

import com.example.clinic.Chat.Chat;
import com.example.clinic.User.*;
import com.example.clinic.Visit.IdVisitClass;
import com.example.clinic.Visit.Visit;

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

    public Patient(Integer id,
                   String name,
                   String surname,
                   Long PESEL,
                   int age,
                   String password,
                   String sex) {
        super(id, name, surname, PESEL, age, password);
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
