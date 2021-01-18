package com.example.clinic.User;

import com.vaadin.tutorial.backend.entity.User;
import com.vaadin.tutorial.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.update(user);
    }



}
