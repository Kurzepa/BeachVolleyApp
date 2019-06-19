package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.*;
import com.example.beachvolleyapp.service.LocationServices;
import com.example.beachvolleyapp.service.TeamService;
import com.example.beachvolleyapp.service.TournamentServices;
import com.example.beachvolleyapp.service.UserService;
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
    TournamentServices tournamentServices;
    @Autowired
    LocationServices locationServices;
    @Autowired
    UserService userService;
    @Autowired
    TeamService teamService;

    @Autowired
    public TournamentController(TournamentServices tournamentServices, LocationServices locationServices, UserService userService){
        this.tournamentServices = tournamentServices;
        this.locationServices = locationServices;
        this.userService = userService;
    }


    @GetMapping("/tournaments")
    public String getToutnaments(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);
        }
        List<Tournament> allTournaments = tournamentServices.findAll();
        List<Location> allLocations = locationServices.findAll();
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

            List<Tournament> myTournaments = tournamentServices.findAllByUser(userService.findByLogin(name));
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
        Tournament tournament = tournamentServices.findById(id).get();
        model.addAttribute("size", tournament.getTeamInTournaments().size());
        model.addAttribute("tournament", tournament);

        return "tournament";
    }

    @PostMapping("/save")
    public String saveTournament(@ModelAttribute Tournament tournament){

        Long locationId = locationServices.save(tournament.getLocation()).getId();
        tournament.setLocation(locationServices.findById(locationId).get());
        tournament.setUser(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        tournamentServices.save(tournament);
        return"redirect:/tournaments";
    }

    @PostMapping("/addToTournament")
    public String addToTournament(@ModelAttribute Tournament tournament, Model model){

        ArrayList<Team> teams = new ArrayList<>();
        // pobranie zalogowanego uzytkownika
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        // dodanie do listu zespołów, do ktorych nalezy zalogowany uzytkownik
        for(Team team: teamService.findAll()){
            if(team.getUsers().contains(user))
                teams.add(team);
        }

        System.out.println(tournament.getId() + tournament.getName());

        model.addAttribute("teams", teams);
        model.addAttribute("tournament", tournament);

        return"choose_team";
    }


}
