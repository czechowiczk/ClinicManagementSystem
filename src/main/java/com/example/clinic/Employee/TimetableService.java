package com.example.clinic.Employee;


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
