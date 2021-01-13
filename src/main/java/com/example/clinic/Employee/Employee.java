package com.example.clinic.Employee;

import com.example.clinic.User.User;

import javax.persistence.*;
import java.util.Set;

@IdClass(IdEmployeeClass.class)
@Entity
@Table(name = "employees")
//@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_user")
//    private Integer userId;
    //@Id
//    @Column(name = "id_employee")
//    private Integer employeeId;

    //@Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee", referencedColumnName = "id_user")
    private User user;

                // łączenie managera z employee
//    @OneToOne(mappedBy = "employee")
//    private Manager manager;

//    @Id
//    @ManyToOne
//    @JoinColumn(name = "id_employee", insertable=false, updatable=false)
//    private Doctor doctor;

//    @OneToMany(mappedBy = "employee")
//    private Set<Salary> salaries;

    @OneToMany(mappedBy = "employee")
    private Set<Timetable> timetables;

    @OneToOne(mappedBy = "employee")
    private Doctor doctor;

    @OneToOne(mappedBy = "employee")
    private AdmEmployee admEmployee;

    public Employee() {
    }

    public Employee(String name, String surname, Long PESEL, int age, String password, Integer id) {
        super(name, surname, PESEL, age, password);
        //this.userId = id;
    }


//    @Override
//    public Integer getId() {
//        return userId;
//    }
//
//    @Override
//    public void setId(Integer id) {
//        this.userId = id;
//    }


}
