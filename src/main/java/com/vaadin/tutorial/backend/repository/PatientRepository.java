package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PatientRepository
        extends JpaRepository<Patient, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into patient (sex, id_patient) values (:sex, :id_patient)", nativeQuery = true)
    void insertPatient(@Param("sex") String sex,
                       @Param("id_patient") Integer id_patient);
}