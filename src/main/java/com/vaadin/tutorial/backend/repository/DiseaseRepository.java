package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    @Query(value = "select * from disease_history d " +
            "where d.id_patient=:patient_id ", nativeQuery = true)

    List<Disease> search(@Param("patient_id") Integer patient_id);
}
