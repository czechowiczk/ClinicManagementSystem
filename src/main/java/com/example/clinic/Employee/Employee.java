package com.example.clinic.Employee;

import com.example.clinic.User.User;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends User {
    @Id
    @Column(name = "id_user")
    private Integer id;
    @Column(name = "id_employee")
    private Integer employeeId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    public Employee() {
    }

    public Employee(Integer employeeId) {
        super();
        this.employeeId = employeeId;
    }

    public Employee(String name, String surname, Long PESEL, int age, String password, Integer id) {
        super(name, surname, PESEL, age, password);
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


}
