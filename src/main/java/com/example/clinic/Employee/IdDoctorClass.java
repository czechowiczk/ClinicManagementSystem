package com.example.clinic.Employee;

import java.io.Serializable;

public class IdDoctorClass implements Serializable {
    private Integer doctorId;
    private Integer employeeId;
    private Integer managerId;

    public IdDoctorClass() {
    }

    public IdDoctorClass(Integer doctorId, Integer employeeId, Integer managerId) {
        this.doctorId = doctorId;
        this.employeeId = employeeId;
        this.managerId = managerId;
    }
}
