package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
