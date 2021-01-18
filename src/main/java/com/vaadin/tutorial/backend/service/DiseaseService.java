package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.entity.Disease;
import com.vaadin.tutorial.backend.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(DiseaseService.class.getName());

    public List<Disease> findAll(Integer patientId) {
        return diseaseRepository.search(patientId);
    }

    public List<Disease> findAll() {
        return diseaseRepository.findAll();
    }

//    public List<Disease> findAll(String filterText) {
//        if(filterText == null || filterText.isEmpty()) {
//            return diseaseRepository.findAll();
//        } else  {
//            return  diseaseRepository.search(filterText);
//        }
//    }

    public long count() {
        return diseaseRepository.count();
    }

    public void delete(Disease disease) {
        diseaseRepository.delete(disease);
    }

    public void save(Disease disease) {
        if (disease == null) {
            LOGGER.log(Level.SEVERE,
                    "Disease is null. Are you sure you have connected your form to the application?");
            return;
        }
        diseaseRepository.save(disease);
    }

}
