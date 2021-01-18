package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.repository.VisitRepository;
import com.vaadin.tutorial.backend.entity.Visit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService implements Dao<Visit>{
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Optional<Visit> getUsersVisits(Integer userId) {
        return visitRepository.findById(userId);
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit update(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public Optional<Visit> get(Integer id) {
        return visitRepository.findById(id);
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public List<Visit> findAll(Integer id) {
        return visitRepository.search(id);
    }


}
