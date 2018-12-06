package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.Team;
import com.example.beachvolleyapp.model.TeamInTournament;
import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.TeamInTournamentRepository;
import com.example.beachvolleyapp.repository.TeamRepository;
import com.example.beachvolleyapp.repository.TournamentRepository;
import com.example.beachvolleyapp.repository.UserRepository;
import com.example.beachvolleyapp.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TeamController {

    @Autowired
    UserService userService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    TeamInTournamentRepository teamInTournamentRepository;

    @GetMapping("/addTeam")
    public String addTeam(Model model) {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);
        }
        model.addAttribute("team", new Team());
        model.addAttribute("users", userService.findAll());
        return"add_team";
    }

    @GetMapping("/saveTeam")
    public String saveTeam(@RequestParam("id") Long id, Model model) {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);

            List<User> users = new ArrayList<>();
            users.add(userService.findById(id));
            users.add(userService.findByLogin(name));

            model.addAttribute("team", new Team(users));
        }

        return"save_team";
    }


    @GetMapping("/saveMyTeam")
    public String saveMyTeam(@RequestParam("teamId") Long teamId, @RequestParam("tourId") Long tournamentId, Model model) {

        Team team = teamRepository.findById(teamId).get();
        Tournament tournament = tournamentRepository.findById(tournamentId).get();

        teamInTournamentRepository.save(new TeamInTournament(tournament, team));

        return"redirect:/tournament?id=" + tournamentId;
    }

    @PostMapping("/saveTeam")
    public String saveTeam(@ModelAttribute @Valid Team team, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            List<User> users = new ArrayList<>();
            for(User user: team.getUsers()){
                users.add(userService.findById(user.getId()));
            }
            team.setUsers(users);
            model.addAttribute("team", team);
            return "save_team";
        }

        teamRepository.save(team);
        return "redirect:/";
    }
}
