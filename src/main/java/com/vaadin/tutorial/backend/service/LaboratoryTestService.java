package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.entity.LaboratoryTest;
import com.vaadin.tutorial.backend.repository.LaboratoryTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryTestService {

    private final LaboratoryTestRepository laboratoryTestRepository;

    public LaboratoryTestService(LaboratoryTestRepository laboratoryTestRepository) {
        this.laboratoryTestRepository = laboratoryTestRepository;
    }

    public List<LaboratoryTest> getTests() {
        return laboratoryTestRepository.findAll();
    }
}
