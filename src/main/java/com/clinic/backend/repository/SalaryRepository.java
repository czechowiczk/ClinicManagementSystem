package com.clinic.backend.repository;

import com.clinic.backend.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    @Query(value = "select * from salary where id_employee = :id", nativeQuery = true)
    Salary checkIfPayed(@Param("id") Integer id);
}
