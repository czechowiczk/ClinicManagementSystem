package com.example.clinic.Employee;

import java.io.Serializable;

public class IdAdmEmployeeClass implements Serializable {
    private Integer admEmployeeId;
    private Integer employeeId;

    public IdAdmEmployeeClass() {
    }

    public IdAdmEmployeeClass(Integer admEmployeeId, Integer employeeId) {
        this.admEmployeeId = admEmployeeId;
        this.employeeId = employeeId;
    }
}
