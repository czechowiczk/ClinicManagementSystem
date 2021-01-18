package com.clinic.backend.service;

import com.clinic.backend.entity.AdmEmployee;
import com.clinic.backend.repository.AdmEmployeeRepository;
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
