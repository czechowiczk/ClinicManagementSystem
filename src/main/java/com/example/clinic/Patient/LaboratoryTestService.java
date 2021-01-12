package com.example.clinic.Patient;

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
