package com.clinic.backend.repository;

import com.clinic.backend.entity.Patient;
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
    @Query(value = "insert into patient (gender, id_patient) values (:gender, :id_patient)", nativeQuery = true)
    void insertPatient(@Param("gender") String sex,
                       @Param("id_patient") Integer id_patient);
}