package com.clinic.backend.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "id_employee")
public class Employee extends User {

    @OneToMany(mappedBy = "employee")
    private Set<Timetable> timetables;


    public Employee() {
    }

    public Employee(String name, String surname, Long PESEL, int age, String password, Role role) {
        super(name, surname, PESEL, age, password, role);
    }

    public Employee(String name, String surname, Long PESEL, int age, String password, Role role, Set<Timetable> timetables) {
        super(name, surname, PESEL, age, password, role);
        this.timetables = timetables;
    }
}
