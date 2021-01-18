package com.clinic.backend.repository;

import com.clinic.backend.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {

    @Query(value = "select * from visits v " +
            "where v.id_patient=:patient_id ", nativeQuery = true)
    List<Visit> search(@Param("patient_id") Integer patient_id);
}
