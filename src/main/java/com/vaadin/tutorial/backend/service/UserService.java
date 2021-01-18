package com.vaadin.tutorial.backend.service;

import com.vaadin.tutorial.backend.entity.User;
import com.vaadin.tutorial.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Dao<User>{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


    //    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        if("user".equals(login)) {
//            return new User(login, "password", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with login: " + login);
//        }
//    }
    @Override
    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Integer id) {
        return userRepository.findAll();
    }

    public void modifyUser(String name, String surname, Integer age, Long pesel) {
        userRepository.modifyUser(name, surname, age, pesel);
    }
}