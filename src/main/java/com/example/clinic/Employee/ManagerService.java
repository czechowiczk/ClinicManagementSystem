package com.example.clinic.Employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getManagers() {
        return managerRepository.findAll();
    }
}
