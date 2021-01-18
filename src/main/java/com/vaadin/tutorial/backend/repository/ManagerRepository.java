package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into manager (id_manager) values (:id_manager)", nativeQuery = true)
    void insertManager(@Param("id_manager") Integer id_manager);
}
