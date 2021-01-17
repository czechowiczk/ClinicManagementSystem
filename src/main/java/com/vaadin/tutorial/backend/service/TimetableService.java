package com.vaadin.tutorial.backend.service;


import com.vaadin.tutorial.backend.entity.Timetable;
import com.vaadin.tutorial.backend.repository.TimetableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public List<Timetable> getTimetables() {
        return timetableRepository.findAll();
    }
}
