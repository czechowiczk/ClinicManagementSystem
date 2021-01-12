package com.example.clinic.Employee;

import com.example.clinic.Patient.IdDiseaseClass;

import javax.persistence.*;

@IdClass(IdAdmEmployeeClass.class)
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class AdmEmployee extends Employee {
    @Id
    @Column(name = "id_admemployee")
    private Integer admEmployeeId;
    @Column(name="id_employee", nullable = false)
    private Integer employeeId;
    @Column(name = "type")
    private String type;
    @Column(name = "id_manager")
    private Integer managerId;

    @Id
    @OneToOne
    @JoinColumn(name = "id_employee", insertable=false, updatable=false)
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_manager", insertable=false, updatable=false)
    private Manager manager;

    public AdmEmployee() {
    }

    public AdmEmployee(String name, String surname, Long PESEL, int age, String password, Integer id, Integer admEmployeeId) {
        super(name, surname, PESEL, age, password, id);
        this.admEmployeeId = admEmployeeId;
    }

    public Integer getAdmEmployeeId() {
        return admEmployeeId;
    }

    public void setAdmEmployeeId(Integer admEmployeeId) {
        this.admEmployeeId = admEmployeeId;
    }

    @Override
    public Integer getEmployeeId() {
        return employeeId;
    }

    @Override
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
