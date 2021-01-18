package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.entity.Disease;
import com.vaadin.tutorial.backend.entity.LaboratoryTest;
import com.vaadin.tutorial.backend.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseService implements Dao<Disease>{
    private final DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> getDiseases() {
        return diseaseRepository.findAll();
    }

    public List<Disease> getTests() {
        return diseaseRepository.findAll();
    }

    @Override
    public Disease save(Disease disease) {
        return diseaseRepository.save(disease);
    }

    @Override
    public Disease update(Disease disease) {
        return diseaseRepository.save(disease);
    }

    @Override
    public void delete(Disease disease) {
        diseaseRepository.delete(disease);
    }

    @Override
    public Optional<Disease> get(Integer id) {
        return diseaseRepository.findById(id);
    }

    @Override
    public List<Disease> findAll() {
        return diseaseRepository.findAll();
    }

    public List<Disease> findAll(Integer id) {
        return diseaseRepository.search(id);
    }
}
