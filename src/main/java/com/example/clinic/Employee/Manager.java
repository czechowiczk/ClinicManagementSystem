package com.example.clinic.Employee;

import com.example.clinic.Patient.Disease;
import com.example.clinic.Patient.IdDiseaseClass;
import com.example.clinic.Patient.Patient;

import javax.persistence.*;
import java.util.Set;

@IdClass(IdManagerClass.class)
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Manager extends Employee{
    @Id
    @Column(name = "id_manager")
    private Integer managerId;
    @Column(name="id_employee", nullable = false)
    private Integer employeeId;

    @Id
    @OneToOne
    @JoinColumn(name = "id_employee", insertable=false, updatable=false)
    private Employee employee;

    @OneToMany(mappedBy = "manager")
    private Set<Doctor> doctors;

    @OneToMany(mappedBy = "manager")
    private Set<AdmEmployee> admEmployees;

    public Manager() {
    }

    public Manager(String name, String surname, Long PESEL, int age, String password, Integer id, Integer managerId) {
        super(name, surname, PESEL, age, password, id);
        this.managerId = managerId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public Integer getEmployeeId() {
        return employeeId;
    }

    @Override
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
