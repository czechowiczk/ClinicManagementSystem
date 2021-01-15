package com.example.clinic.Employee;

import com.example.clinic.Chat.Chat;
import com.example.clinic.Patient.Disease;
import com.example.clinic.Patient.IdDiseaseClass;
import com.example.clinic.Patient.Patient;
import com.example.clinic.Visit.Visit;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "id_doctor")
public class Doctor extends Employee {
    @Column(name="specialization")
    private String specialization;
    @Column(name="id_manager")
    private Integer managerId;

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;

    @OneToMany(mappedBy = "doctor")
    private Set<Chat> chats;

    @ManyToOne
    @JoinColumn(name = "id_manager", referencedColumnName = "id_manager", insertable=false, updatable=false)
    private Manager manager;

    public Doctor() {
    }

    public Doctor(String name, String surname, Long PESEL, int age, String password, Integer id, String specialization, Integer managerId, Employee employee, Set<Visit> visits) {
        super(name, surname, PESEL, age, password);
        this.specialization = specialization;
        this.managerId = managerId;
        this.visits = visits;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

}
