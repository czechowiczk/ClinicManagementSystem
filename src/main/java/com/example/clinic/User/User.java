package com.example.clinic.User;

import com.example.clinic.Employee.Employee;
import com.example.clinic.Patient.Patient;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @javax.persistence.Id
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "pesel")
    private Long PESEL;
    @Column(name = "age")
    private Integer age;
    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user")
    private Patient patient;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    public User() {
    }

    public User(Integer id,
                String name,
                String surname,
                Long PESEL,
                Integer age,
                String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.age = age;
        this.password = password;
    }

    public User(String name,
                String surname,
                Long PESEL,
                int age,
                String password) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.age = age;
        this.password = password;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPESEL() {
        return PESEL;
    }

    public void setPESEL(long PESEL) {
        this.PESEL = PESEL;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", PESEL=" + PESEL +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }


}


