package com.clinic.backend.service;

import com.clinic.backend.entity.Salary;
import com.clinic.backend.repository.SalaryRepository;
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
