package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.Location;
import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.repository.LocationRepository;
import com.example.beachvolleyapp.repository.TournamentRepository;
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
    public TournamentController(TournamentRepository tournamentRepository, LocationRepository locationRepository){
        this.tournamentRepository = tournamentRepository;
        this.locationRepository = locationRepository;
    }


    @GetMapping("/tournament")
    public String getToutnaments(Model model) {
        List<Tournament> allTournaments = tournamentRepository.findAll();
        List<Location> allLocations = locationRepository.findAll();
        model.addAttribute("tournaments", allTournaments);
        model.addAttribute("locations", allLocations);

        return"tournament";
    }

    @GetMapping("/addTournament")
    public String addTournament(Model model) {
        model.addAttribute("tournament", new Tournament());
        return"add_tournament";
    }

    @PostMapping("/save")
    public String saveTournament(@ModelAttribute Tournament tournament){
        System.out.println("Add tour");
        tournamentRepository.save(tournament);
        return"redirect:/";
    }

}
