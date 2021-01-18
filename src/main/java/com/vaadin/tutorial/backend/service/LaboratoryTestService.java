package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.entity.LaboratoryTest;
import com.vaadin.tutorial.backend.repository.LaboratoryTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LaboratoryTestService {

    private final LaboratoryTestRepository laboratoryTestRepository;

    public List<LaboratoryTest> getTests() {
        return laboratoryTestRepository.findAll();
    }

    @Autowired
    public LaboratoryTestService(LaboratoryTestRepository laboratoryTestRepository) {
        this.laboratoryTestRepository = laboratoryTestRepository;
    }

    private static final Logger LOGGER = Logger.getLogger(LaboratoryTestService.class.getName());

    public List<LaboratoryTest> findAll(Integer patientId) {
        return laboratoryTestRepository.search(patientId);
    }

    public List<LaboratoryTest> findAll() {
        return laboratoryTestRepository.findAll();
    }

    public long count() {
        return laboratoryTestRepository.count();
    }

    public void delete(LaboratoryTest laboratoryTest) {
        laboratoryTestRepository.delete(laboratoryTest);
    }

    public void save(LaboratoryTest laboratoryTest) {
        if (laboratoryTest == null) {
            LOGGER.log(Level.SEVERE,
                    "Laboratory test is null. Are you sure you have connected your form to the application?");
            return;
        }
        laboratoryTestRepository.save(laboratoryTest);
    }
}
