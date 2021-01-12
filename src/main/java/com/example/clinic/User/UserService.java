package com.example.clinic.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getUsers() {
        return List.of(
                new User(1,
                        "Bartek",
                        "Czupta",
                        8015013718L,
                        41,
                        "ukraina")
        );
    }
}
