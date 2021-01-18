package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.entity.LaboratoryTest;
import com.vaadin.tutorial.backend.entity.Visit;
import com.vaadin.tutorial.backend.repository.LaboratoryTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LaboratoryTestService implements Dao<LaboratoryTest>{

    private final LaboratoryTestRepository laboratoryTestRepository;

    public LaboratoryTestService(LaboratoryTestRepository laboratoryTestRepository) {
        this.laboratoryTestRepository = laboratoryTestRepository;
    }

    public List<LaboratoryTest> getTests() {
        return laboratoryTestRepository.findAll();
    }

    @Override
    public LaboratoryTest save(LaboratoryTest laboratoryTest) {
        return laboratoryTestRepository.save(laboratoryTest);
    }

    @Override
    public LaboratoryTest update(LaboratoryTest laboratoryTest) {
        return laboratoryTestRepository.save(laboratoryTest);
    }

    @Override
    public void delete(LaboratoryTest laboratoryTest) {
        laboratoryTestRepository.delete(laboratoryTest);
    }

    @Override
    public Optional<LaboratoryTest> get(Integer id) {
        return laboratoryTestRepository.findById(id);
    }

    @Override
    public List<LaboratoryTest> findAll() {
        return laboratoryTestRepository.findAll();
    }

    public List<LaboratoryTest> findAll(Integer id) {
        return laboratoryTestRepository.search(id);
    }
}
