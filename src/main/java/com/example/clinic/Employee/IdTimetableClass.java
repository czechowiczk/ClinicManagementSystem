package com.example.clinic.Employee;

import java.io.Serializable;

public class IdTimetableClass implements Serializable {
    private Integer timetableId;
    private Integer employeeId;

    public IdTimetableClass() {
    }

    public IdTimetableClass(Integer timetableId, Integer employeeId) {
        this.timetableId = timetableId;
        this.employeeId = employeeId;
    }
}
