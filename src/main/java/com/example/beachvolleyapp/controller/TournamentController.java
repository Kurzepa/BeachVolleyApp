package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.Location;
import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.repository.LocationRepository;
import com.example.beachvolleyapp.repository.TournamentRepository;
import com.example.beachvolleyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TournamentController {

    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public TournamentController(TournamentRepository tournamentRepository, LocationRepository locationRepository, UserRepository userRepository){
        this.tournamentRepository = tournamentRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/tournaments")
    public String getToutnaments(Model model) {
        List<Tournament> allTournaments = tournamentRepository.findAll();
        List<Location> allLocations = locationRepository.findAll();
        model.addAttribute("tournaments", allTournaments);
        model.addAttribute("locations", allLocations);

        return "tournaments";
    }

    @GetMapping("/addTournament")
    public String addTournament(Model model) {
        model.addAttribute("tournament", new Tournament(new Location()));
        return"add_tournament";
    }

    @GetMapping("/myTournaments")
    public String myTournament(Model model){
        List<Tournament> myTournaments = tournamentRepository.findAllByUser(userRepository.findById(1L).get());
        model.addAttribute("tournaments", myTournaments );
        return "my_tour";
    }

    @GetMapping("/tournament")
    public String Tournament (Model model){
        List<Tournament> Tournament = tournamentRepository.findFirstById(1L);
        model.addAttribute("tournament", Tournament);
        return "tournament";
    }

    @PostMapping("/save")
    public String saveTournament(@ModelAttribute Tournament tournament){

        Long locationId = locationRepository.save(tournament.getLocation()).getId();
        tournament.setLocation(locationRepository.findById(locationId).get());
        tournamentRepository.save(tournament);
        return"redirect:/tournament";
    }

}
