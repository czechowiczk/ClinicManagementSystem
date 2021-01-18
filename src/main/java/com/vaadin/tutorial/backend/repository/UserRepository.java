package com.vaadin.tutorial.backend.repository;

import com.vaadin.tutorial.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update user set name = :name, surname = :surname, age = :age, pesel = :pesel", nativeQuery = true)
    void modifyUser(@Param("name") String name, @Param("surname") String surname, @Param("age") Integer age, @Param("pesel") Long pesel);

    public User getByPESEL(Long PESEL);

}
