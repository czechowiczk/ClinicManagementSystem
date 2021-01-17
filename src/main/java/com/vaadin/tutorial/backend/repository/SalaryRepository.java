package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
}
