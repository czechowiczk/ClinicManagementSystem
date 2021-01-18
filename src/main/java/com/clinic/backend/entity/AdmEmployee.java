package com.clinic.backend.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "administration_employees")
public class AdmEmployee extends Employee {
    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_manager", insertable=false, updatable=false)
    private Manager manager;

    public AdmEmployee() {
    }

    public AdmEmployee(String name, String surname, Long PESEL, int age, String password, Role role, Set<Timetable> timetables) {
        super(name, surname, PESEL, age, password, role, timetables);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
