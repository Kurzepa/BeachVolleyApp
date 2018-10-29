package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TournamentController {

    @Autowired
    TournamentRepository tournamentRepository;

    @GetMapping("/tournament")
    public String getToutnaments(Model model) {
        List<Tournament> allTournaments = tournamentRepository.findAll();
        model.addAttribute("tournament", allTournaments);

        return"tournament";
    }

}
