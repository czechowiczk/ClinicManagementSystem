package com.clinic.backend.repository;

import com.clinic.backend.entity.AdmEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdmEmployeeRepository extends JpaRepository<AdmEmployee, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into administration_employees (type, id_employee, id_manager) values (:type, :id_employee, :id_manager)", nativeQuery = true)
    void insertAdmEmployee(@Param("type") String type,
                      @Param("id_employee") Integer id_doctor,
                      @Param("id_manager") Integer id_manager
                      );
}
