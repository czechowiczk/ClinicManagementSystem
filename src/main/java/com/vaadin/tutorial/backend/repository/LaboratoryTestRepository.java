package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Disease;
import com.vaadin.tutorial.backend.entity.LaboratoryTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LaboratoryTestRepository extends JpaRepository<LaboratoryTest, Integer> {
    @Query(value = "select * from laboratory_tests l " +
            "where l.id_patient=:patient_id ", nativeQuery = true)
    List<LaboratoryTest> search(@Param("patient_id") Integer patient_id);
}
