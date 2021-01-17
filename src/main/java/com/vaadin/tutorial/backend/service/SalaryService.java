package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.entity.Salary;
import com.vaadin.tutorial.backend.repository.SalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {
    private final SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public List<Salary> getSalaries() {
        return salaryRepository.findAll();
    }
}
