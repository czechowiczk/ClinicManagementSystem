package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

}
