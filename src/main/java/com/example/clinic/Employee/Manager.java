package com.example.clinic.Employee;

import com.example.clinic.Patient.Disease;
import com.example.clinic.Patient.IdDiseaseClass;
import com.example.clinic.Patient.Patient;

import javax.persistence.*;
import java.util.Set;

//@IdClass(IdManagerClass.class)
@Entity
@Table
//@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "id_manager")
public class Manager extends Employee {
    //@Id
    @Column(name="id_employee")
    private Integer employeeId;

//    @Id       // Łączenie managera z employee
//    @OneToOne
//    @JoinColumn(name = "id_employee", insertable=false, updatable=false)
//    private Employee employee;

    @OneToMany(mappedBy = "manager")
    private Set<Doctor> doctors;

    @OneToMany(mappedBy = "manager")
    private Set<AdmEmployee> admEmployees;

    public Manager() {
    }

    public Manager(String name, String surname, Long PESEL, int age, String password) {
        super(name, surname, PESEL, age, password);
    }
}
