package com.example.clinic.Patient;

import com.example.clinic.Chat.Chat;
import com.example.clinic.User.*;
import com.example.clinic.Visit.IdVisitClass;
import com.example.clinic.Visit.Visit;

import javax.persistence.*;
import java.util.Set;

@IdClass(IdPatientClass.class)
@Entity
@Table(name = "patients")
//@Inheritance(strategy = InheritanceType.JOINED)
public class Patient extends User{

    @Column(name = "sex")
    private String sex;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient", referencedColumnName = "id_user")
    private User user;

    @OneToMany(mappedBy="patient")
    private Set<Disease> diseases;

    @OneToMany(mappedBy="patient")
    private Set<LaboratoryTest> tests;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;

    @OneToMany(mappedBy = "patient")
    private Set<Chat> chats;


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
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
