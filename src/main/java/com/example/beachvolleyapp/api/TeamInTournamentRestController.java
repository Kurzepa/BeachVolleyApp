package com.example.beachvolleyapp.api;

import com.example.beachvolleyapp.model.TeamInTournament;
import com.example.beachvolleyapp.service.TeamInTournamentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teamintour")
@CrossOrigin
public class TeamInTournamentRestController {

    TeamInTournamentServices teamInTournamentServices;

    @Autowired
    public TeamInTournamentRestController(TeamInTournamentServices teamInTournamentServices) {
        this.teamInTournamentServices = teamInTournamentServices;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeamInTournament> getTeamInTournaments(){
        return teamInTournamentServices.findAll();
    }
}
