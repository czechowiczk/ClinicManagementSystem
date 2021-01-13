package com.example.clinic.Employee;

import java.io.Serializable;

public class IdDoctorClass implements Serializable {
    private Integer doctorId;
    private Integer employeeId;

    public IdDoctorClass() {
    }

    public IdDoctorClass(Integer doctorId, Integer employeeId) {
        this.doctorId = doctorId;
        this.employeeId = employeeId;
    }
}
