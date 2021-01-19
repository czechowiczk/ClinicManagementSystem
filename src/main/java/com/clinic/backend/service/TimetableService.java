package com.clinic.backend.service;


import com.clinic.backend.entity.Timetable;
import com.clinic.backend.repository.TimetableRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableService implements Dao<Timetable>{
    private final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public List<Timetable> getTimetables() {
        return timetableRepository.findAll();
    }

    @Override
    public Timetable save(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    @Override
    public Timetable update(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    @Override
    public void delete(Timetable timetable) {
        timetableRepository.delete(timetable);
    }

    @Override
    public Optional<Timetable> get(Integer id) {
        return timetableRepository.findById(id);
    }

    @Override
    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    @Override
    public List<Timetable> findAll(Integer id) {
        return null;
    }

   public List<Timetable> getEmployeesTimetable(@Param("id_manager") Integer id_manager) {
        return timetableRepository.getEmployeesTimetable(id_manager);
   }

    public List<Timetable> getDoctorsTimetables(Integer userId) { return timetableRepository.findAllDoctorsTimetables(userId); }
}
