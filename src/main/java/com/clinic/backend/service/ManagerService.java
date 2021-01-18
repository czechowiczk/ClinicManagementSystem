package com.clinic.backend.service;

import com.clinic.backend.entity.Manager;
import com.clinic.backend.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService implements Dao<Manager>{
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager update(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public void delete(Manager manager) {
        managerRepository.delete(manager);
    }

    @Override
    public Optional<Manager> get(Integer id) {
        return managerRepository.findById(id);
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public List<Manager> findAll(Integer id) {
        return null;
    }
}
