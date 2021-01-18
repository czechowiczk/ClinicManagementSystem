package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Employee;
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
    @Query(value = "insert into employees (id_employee) values (:id_user)", nativeQuery = true)
    void insertUser(@Param("id_user") Integer id_user);
}
