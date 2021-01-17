package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {

}
