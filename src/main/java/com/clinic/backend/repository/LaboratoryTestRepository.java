package com.clinic.backend.repository;

import com.clinic.backend.entity.LaboratoryTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryTestRepository extends JpaRepository<LaboratoryTest, Integer> {
    @Query(value = "select * from laboratory_tests l " +
            "where l.id_patient=:patient_id ", nativeQuery = true)
    List<LaboratoryTest> search(@Param("patient_id") Integer patient_id);


//    @Transactional
//    @Modifying
//    @Query(value = "insert into laboratory_tests (")
}
