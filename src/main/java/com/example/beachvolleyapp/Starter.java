package com.example.beachvolleyapp;


import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Kotek", "koteczek12", "Kasia", "Nowak", 12, "female");
        userRepository.save(user);
    }
}
