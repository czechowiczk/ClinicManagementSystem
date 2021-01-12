package com.example.clinic.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table
public class User {
    @javax.persistence.Id
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    private Integer id;
    private String name;
    private String surname;
    private Long PESEL;
    private Integer age;
    private String password;


    public User() {

    }

    public User(int id,
                String name,
                String surname,
                Long PESEL,
                int age,
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

    public int getAge() {
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
