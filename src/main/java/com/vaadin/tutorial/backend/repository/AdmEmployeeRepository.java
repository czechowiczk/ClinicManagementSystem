package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.AdmEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmEmployeeRepository extends JpaRepository<AdmEmployee, Integer> {
}
