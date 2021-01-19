package com.clinic.backend.repository;

import com.clinic.backend.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

    @Query(value = "select * from timetable where id_employee = :idEmployee", nativeQuery = true)
    List<Timetable> findAllDoctorsTimetables(@Param("idEmployee") Integer idEmployee);

    @Query(value = "select t.id_timetable, t.date, t.start_hour, t.end_hour, t.id_employee from timetable t join employees e on t.id_employee=e.id_employee " +
            "join doctors d on e.id_employee = d.id_doctor join manager m on d.id_manager = m.id_manager where d.id_manager = :id_manager union select t.id_timetable, t.date, t.start_hour, t.end_hour, t.id_employee from timetable t join employees e on t.id_employee=e.id_employee "+
            "join administration_employees a on e.id_employee = a.id_employee join manager m on a.id_manager = m.id_manager where a.id_manager = :id_manager", nativeQuery = true)
    List<Timetable> getEmployeesTimetable(@Param("id_manager") Integer id_manager);

    @Query(value = "select * from timetable where id_employee = :idDoctor and date = :date and :hour between start_hour and end_hour", nativeQuery = true)
    Timetable getDoctorsWorkingHour(@Param("idDoctor") Integer id, @Param("date") LocalDate date, @Param("hour") LocalTime time);

    @Query(value = "SELECT sum(end_hour - start_hour)/10000 FROM timetable where id_employee = :id", nativeQuery = true)
    Integer getWorkHours(@Param("id") Integer id);
}
