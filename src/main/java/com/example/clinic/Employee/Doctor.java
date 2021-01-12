package com.example.clinic.Employee;

import com.example.clinic.Chat.Chat;
import com.example.clinic.Patient.Disease;
import com.example.clinic.Patient.IdDiseaseClass;
import com.example.clinic.Visit.Visit;

import javax.persistence.*;
import java.util.Set;

@IdClass(IdDoctorClass.class)
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Doctor extends Employee {
    @Id
    @Column(name="id_doctor")
    private Integer doctorId;
    @Column(name="id_employee", nullable = false)
    private Integer employeeId;
    @Column(name="specialization")
    private String specialization;
    @Column(name="id_manager")
    private Integer managerId;

    @Id
    @OneToOne
    @JoinColumn(name = "id_employee", insertable=false, updatable=false)
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_manager", insertable=false, updatable=false)
    private Manager manager;

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;

    @OneToMany(mappedBy = "doctor")
    private Set<Chat> chats;

    public Doctor() {
    }

    public Doctor(String name, String surname, Long PESEL, int age, String password, Integer id) {
        super(name, surname, PESEL, age, password, id);
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public Integer getEmployeeId() {
        return employeeId;
    }

    @Override
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
