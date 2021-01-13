package com.example.clinic.Employee;

import java.io.Serializable;

public class IdEmployeeClass implements Serializable {
    private Integer userId;
    private Integer employeeId;

    public IdEmployeeClass() {
    }

    public IdEmployeeClass(Integer userId, Integer employeeId) {
        this.userId = userId;
        this.employeeId = employeeId;
    }
}
