package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.LaboratoryTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryTestRepository extends JpaRepository<LaboratoryTest, Integer> {

}
