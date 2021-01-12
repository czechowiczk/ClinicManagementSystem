package com.example.clinic.Patient;

import com.example.clinic.User.*;

import javax.persistence.*;


@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Patient extends User {

    @Id
    private Integer id;
    private Integer patientId;
    private String sex;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;


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
