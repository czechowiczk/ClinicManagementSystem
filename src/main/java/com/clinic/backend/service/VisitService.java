package com.clinic.backend.service;

import com.clinic.backend.entity.Visit;
import com.clinic.backend.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    @Override
    public List<Visit> findAll(Integer id) {
        return null;
    }

    public List<Visit> findAllPatientsVisits(Integer id) {
        return visitRepository.searchPatientsVisit(id);
    }

    public List<Visit> findAllDoctorsVisits(Integer id) { return visitRepository.searchDoctorsVisit(id); }

    public List<Date> findAllVisitsDates() { return visitRepository.getVisitsDates(); }

    public void deleteVisitByDate(Date date) {
        visitRepository.deleteVisitByDate(date);
    }

}
