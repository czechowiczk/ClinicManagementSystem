package com.vaadin.tutorial.backend.entity;

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

    public Doctor(String name, String surname, Long PESEL, int age, String password, Role role, Set<Timetable> timetables, String specialization, Integer managerId, Set<Visit> visits) {
        super(name, surname, PESEL, age, password, role, timetables);
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
