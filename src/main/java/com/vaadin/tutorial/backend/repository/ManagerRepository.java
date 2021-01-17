package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
