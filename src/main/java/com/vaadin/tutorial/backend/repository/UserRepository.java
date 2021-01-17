package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<User, Integer> {

    public User getByPESEL(Long PESEL);

}
