package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Integer> {

//    @Query("select v from visits v where v.id_patient = :id_patient")
//    Visit findByPatientId
}
