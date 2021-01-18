package com.clinic.backend.repository;

import com.clinic.backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into doctors (id_manager, specialization, id_doctor) values (:id_manager, :specialization, :id_employee)", nativeQuery = true)
    void insertDoctor(@Param("id_manager") Integer id_manager,
                      @Param("specialization") String specialization,
                      @Param("id_employee") Integer id_doctor);
}
