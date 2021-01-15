package com.example.clinic.Employee;

import com.example.clinic.User.User;

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

    public Employee(String name, String surname, Long PESEL, int age, String password) {
        super(name, surname, PESEL, age, password);
    }

    public Employee(String name, String surname, Long PESEL, int age, String password, Set<Timetable> timetables) {
        super(name, surname, PESEL, age, password);
        this.timetables = timetables;
    }

}
