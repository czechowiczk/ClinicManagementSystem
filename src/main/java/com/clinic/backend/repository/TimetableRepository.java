package com.clinic.backend.repository;

import com.clinic.backend.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

    @Query(value = "select * from timetable where id_employee = :idEmployee", nativeQuery = true)
    List<Timetable> findAllDoctorsTimetables(@Param("idEmployee") Integer idEmployee);
}
