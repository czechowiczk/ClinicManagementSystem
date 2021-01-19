package com.clinic.backend.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "id_employee")
public class Employee extends User {

    @Column(name = "rate")
    Integer rate;
    @OneToMany(mappedBy = "employee")
    private Set<Timetable> timetables;


    public Employee() {
    }

    public Employee(String name, String surname, Long PESEL, int age, String password, Role role) {
        super(name, surname, PESEL, age, password, role);
    }

    public Employee(String name, String surname, Long PESEL, int age, String password, Role role, Set<Timetable> timetables, Integer rate) {
        super(name, surname, PESEL, age, password, role);
        this.timetables = timetables;
        this.rate = rate;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
