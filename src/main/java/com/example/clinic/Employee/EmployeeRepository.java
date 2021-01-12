package com.example.clinic.Employee;

import com.example.clinic.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {
}
