package com.example.beachvolleyapp;


import com.example.beachvolleyapp.model.*;
import com.example.beachvolleyapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TeamInTournamentRepository teamInTournamentRepository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                new User("Kotek", passwordEncoder.encode("koteczek12"), "Kasia", "Nowak", 12, "female", "kasia@gmail.com", 60),
                new User("Siatkarz", passwordEncoder.encode("siatka3"), "Wojciech", "Mielczarek", 15, "male", "wojtek@gmail.com", 26),
                new User("Raz", passwordEncoder.encode("razdwa3"), "Aleksandra", "Kowalska", 23, "female", "ola@gmail.com", 126),
                new User("Kotek", passwordEncoder.encode("koteczek12"), "Krzysztof", "Kowalski", 12, "male", "krzysiek@gmail.com", 80),
                new User("Siatkarz", passwordEncoder.encode("siatka3"), "Natalia", "Zielony", 15, "female", "nata@gmail.com", 14),
                new User("Dwa", passwordEncoder.encode("razdwa3"), "Tomasz", "Lewandowski", 23, "male", "tom@gmail.com", 62)
        ).forEach(userRepository::save);

        Stream.of(
                //Location(String street, int number, String city)
                new Location("Poniatowskiego",12,"Oborniki"),
                new Location("Zachodnia",18,"Wrocław"),
                new Location("Piaskowa", 20, "Wałbrzych")
        ).forEach(locationRepository::save);
        Stream.of(
                 //Tournament(String name, Date date, Date time, String category, int sitesNumber, int fee, Location location)
                 //Location(String street, int number, String city)
                 new Tournament("OMOS", new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-18"),
                         new SimpleDateFormat("HH:mm").parse("10:00"),"Open", 10, 10,
                         locationRepository.findById(1L).get(), userRepository.findById(1L).get()),
                 new Tournament("Spartan",new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-18"),
                         new SimpleDateFormat("HH:mm").parse("12:00"),"Open", 20, 10,
                         locationRepository.findById(2L).get(), userRepository.findById(2L).get()),
                 new Tournament("OMW",new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-18"),
                         new SimpleDateFormat("HH:mm").parse("10:00"),"Open", 30, 10,
                         locationRepository.findById(3L).get(), userRepository.findById(1L).get())
        ).forEach(tournamentRepository::save);

        ArrayList<User> users = new ArrayList<User>();
        users.add(userRepository.findById(1L).get());
        users.add(userRepository.findById(2L).get());

        Stream.of(
                new Team("bobki", users)
        ).forEach(teamRepository::save);

        Stream.of(
                new TeamInTournament( tournamentRepository.findById(2L).get(), teamRepository.findById(1L).get())
        ).forEach(teamInTournamentRepository::save);
    }
}
