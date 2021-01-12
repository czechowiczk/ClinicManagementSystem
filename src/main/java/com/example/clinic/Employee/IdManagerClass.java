package com.example.clinic.Employee;

import java.io.Serializable;

public class IdManagerClass implements Serializable {
    private Integer managerId;
    private Integer employeeId;

    public IdManagerClass() {
    }

    public IdManagerClass(Integer managerId, Integer employeeId) {
        this.managerId = managerId;
        this.employeeId = employeeId;
    }
}
