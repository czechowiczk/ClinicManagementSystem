package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.entity.Disease;
import com.vaadin.tutorial.backend.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> getDiseases() {
        return diseaseRepository.findAll();
    }

}
