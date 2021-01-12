package com.example.clinic.Visit;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> getUsersVisits(Integer userId) {
        return visitRepository.findAll();
    }

    public void bookVisit(Integer userId) {
        System.out.println(userId);
    }

}
