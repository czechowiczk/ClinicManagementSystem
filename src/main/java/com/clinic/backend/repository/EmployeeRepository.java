package com.clinic.backend.repository;

import com.clinic.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into employees (id_employee, rate) values (:id_user, :rate)", nativeQuery = true)
    void insertUser(@Param("id_user") Integer id_user, @Param("rate") Integer rate);


}
