package com.example.clinic.Employee;

import java.io.Serializable;

public class IdAdmEmployeeClass implements Serializable {
    private Integer admEmployeeId;
    private Integer employeeId;
    private Integer managerId;

    public IdAdmEmployeeClass() {
    }

    public IdAdmEmployeeClass(Integer admEmployeeId, Integer employeeId, Integer managerId) {
        this.admEmployeeId = admEmployeeId;
        this.employeeId = employeeId;
        this.managerId = managerId;
    }
}
