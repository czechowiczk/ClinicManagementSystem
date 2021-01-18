package com.clinic.backend.entity;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Salary {
    @Id
    @Column(name="id_entry")
    private Integer entryId;
    @Column(name="id_manager")
    private Integer managerId;
    @Column(name="id_employee", nullable = false)
    private Integer employeeId;
    @Column(name="hours_amount")
    private Integer hoursAmount;
    @Column(name="cash_amount")
    private Integer cashAmount;


    //@Id
    @ManyToOne
    @JoinColumn(name = "id_employee", insertable=false, updatable=false)
    private Employee employee;

    //@Id
    @ManyToOne
    @JoinColumn(name = "id_manager", insertable=false, updatable=false)
    private Manager manager;

    public Salary() {
    }

    public Salary(Integer managerId, Integer employeeId, Integer hoursAmount, Integer cashAmount, Employee employee, Manager manager) {
        this.managerId = managerId;
        this.employeeId = employeeId;
        this.hoursAmount = hoursAmount;
        this.cashAmount = cashAmount;
        this.employee = employee;
        this.manager = manager;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getHoursAmount() {
        return hoursAmount;
    }

    public void setHoursAmount(Integer hoursAmount) {
        this.hoursAmount = hoursAmount;
    }

    public Integer getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Integer cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
