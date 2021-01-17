package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {
}
