package com.example.clinic.Salary;

import java.io.Serializable;

public class IdSalaryClass implements Serializable {
    private Integer entryId;
    private Integer managerId;
    private Integer employeeId;


    public IdSalaryClass() {
    }

    public IdSalaryClass(Integer entryId, Integer managerId, Integer employeeId) {
        this.entryId = entryId;
        this.managerId = managerId;
        this.employeeId = employeeId;
    }
}
