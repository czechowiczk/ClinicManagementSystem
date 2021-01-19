package com.clinic.backend.repository;

import com.clinic.backend.entity.User;
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
    @Query(value = "update user set name = :name, surname = :surname, age = :age where id_user = :id_user", nativeQuery = true)
    void modifyUser(@Param("name") String name, @Param("surname") String surname, @Param("age") Integer age, @Param("id_user") Integer id_user);

    public User getByPESEL(Long PESEL);

}
