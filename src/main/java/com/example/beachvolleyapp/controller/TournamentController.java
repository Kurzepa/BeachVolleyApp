package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.*;
import com.example.beachvolleyapp.repository.LocationRepository;
import com.example.beachvolleyapp.repository.TeamRepository;
import com.example.beachvolleyapp.repository.TournamentRepository;
import com.example.beachvolleyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    TeamRepository teamRepository;

    @Autowired
    public TournamentController(TournamentRepository tournamentRepository, LocationRepository locationRepository, UserRepository userRepository){
        this.tournamentRepository = tournamentRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/tournaments")
    public String getToutnaments(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);
        }
        List<Tournament> allTournaments = tournamentRepository.findAll();
        List<Location> allLocations = locationRepository.findAll();
        model.addAttribute("tournaments", allTournaments);
        model.addAttribute("locations", allLocations);

        return "tournaments";
    }


    @GetMapping("/addTournament")
    public String addTournament(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);
        }
        model.addAttribute("tournament", new Tournament(new Location()));
        return"add_tournament";
    }

    @GetMapping("/myTournaments")
    public String myTournament(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);

            List<Tournament> myTournaments = tournamentRepository.findAllByUser(userRepository.findByLogin(name));
            model.addAttribute("tournaments", myTournaments );
        }

        return "my_tour";
    }

    @GetMapping("/tournament")
    public String Tournament (@RequestParam("id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);
        }
        Tournament tournament = tournamentRepository.findById(id).get();
        model.addAttribute("size", tournament.getTeamInTournaments().size());
        model.addAttribute("tournament", tournament);

        return "tournament";
    }

    @PostMapping("/save")
    public String saveTournament(@ModelAttribute Tournament tournament){

        Long locationId = locationRepository.save(tournament.getLocation()).getId();
        tournament.setLocation(locationRepository.findById(locationId).get());
        tournament.setUser(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        tournamentRepository.save(tournament);
        return"redirect:/tournaments";
    }

    @PostMapping("/addToTournament")
    public String addToTournament(@ModelAttribute Tournament tournament, Model model){

        ArrayList<Team> teams = new ArrayList<>();
        // pobranie zalogowanego uzytkownika
        User user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        // dodanie do listu zespołów, do ktorych nalezy zalogowany uzytkownik
        for(Team team: teamRepository.findAll()){
            if(team.getUsers().contains(user))
                teams.add(team);
        }

        System.out.println(tournament.getId() + tournament.getName());

        model.addAttribute("teams", teams);
        model.addAttribute("tournament", tournament);

        return"choose_team";
    }


}
