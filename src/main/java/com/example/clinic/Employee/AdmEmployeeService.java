package com.example.clinic.Employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmEmployeeService {
    private final AdmEmployeeRepository admEmployeeRepository;

    public AdmEmployeeService(AdmEmployeeRepository admEmployeeRepository) {
        this.admEmployeeRepository = admEmployeeRepository;
    }

    public List<AdmEmployee> getAdmEmployees() {
        return admEmployeeRepository.findAll();
    }
}
