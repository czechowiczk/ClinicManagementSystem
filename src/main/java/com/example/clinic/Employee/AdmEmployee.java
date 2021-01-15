package com.example.clinic.Employee;

import javax.persistence.*;


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

    public AdmEmployee(String name, String surname, Long PESEL, int age, String password, Integer admEmployeeId) {
        super(name, surname, PESEL, age, password);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
