package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
