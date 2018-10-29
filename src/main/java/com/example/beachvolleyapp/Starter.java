package com.example.beachvolleyapp;


import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.TournamentRepository;
import com.example.beachvolleyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Kotek", "koteczek12", "Kasia", "Nowak", 12, "female");
        userRepository.save(user);
        Stream.of(
                 new Tournament("OMOS","Open", 10, 10),
                 new Tournament("Spartan","Open", 20, 10),
                 new Tournament("OMW","Open", 30, 10)
        ).forEach(tournamentRepository::save);
    }
}
