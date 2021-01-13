package com.example.clinic.Employee;

import com.example.clinic.Patient.IdDiseaseClass;

import javax.persistence.*;
import java.time.LocalTime;

@IdClass(IdTimetableClass.class)
@Entity
@Table(name = "timetable")
@Inheritance(strategy = InheritanceType.JOINED)
public class Timetable {
    @Id
    @Column(name="id_timetable")
    private Integer timetableId;
    @Column(name="id_employee", nullable = false)
    private Integer employeeId;
    @Column(name="date")
    private String date;
    @Column(name="start_hour")
    private LocalTime startHour;
    @Column(name="end_hour")
    private LocalTime endHour;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_employee", insertable=false, updatable=false)
    private Employee employee;

    public Timetable() {
    }

    public Timetable(Integer employeeId, String date, LocalTime startHour, LocalTime endHour, Employee employee) {
        this.employeeId = employeeId;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.employee = employee;
    }

    public Integer getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Integer timetableId) {
        this.timetableId = timetableId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
