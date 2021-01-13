package com.example.clinic.Employee;

import com.example.clinic.Patient.IdDiseaseClass;

import javax.persistence.*;

@IdClass(IdAdmEmployeeClass.class)
@Entity
@Table(name = "administration_employees")
@Inheritance(strategy = InheritanceType.JOINED)
public class AdmEmployee extends Employee {
    @Column(name = "type")
    private String type;

    @OneToOne
    @JoinColumn(name = "id_admemployee", referencedColumnName = "id_employee", insertable=false, updatable=false)
    private Employee employee;

//    @Id
    @ManyToOne
    @JoinColumn(name = "id_manager", insertable=false, updatable=false)
    private Manager manager;

    public AdmEmployee() {
    }

    public AdmEmployee(String name, String surname, Long PESEL, int age, String password, Integer id, Integer admEmployeeId) {
        super(name, surname, PESEL, age, password, id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
