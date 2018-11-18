package com.example.beachvolleyapp;


import com.example.beachvolleyapp.model.Location;
import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.LocationRepository;
import com.example.beachvolleyapp.repository.TournamentRepository;
import com.example.beachvolleyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.stream.Stream;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                new User("Kotek", passwordEncoder.encode("koteczek12"), "Kasia", "Nowak", 12, "female", "kasia@gmail.com"),
                new User("Siatkarz", passwordEncoder.encode("siatka3"), "Wojciech", "Mielczarek", 15, "male", "wojtek@gmail.com"),
                new User("Raz", passwordEncoder.encode("razdwa3"), "Aleksandra", "Kowalska", 23, "female", "ola@gmail.com")
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
    }
}
