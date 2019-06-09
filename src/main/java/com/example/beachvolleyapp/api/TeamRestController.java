package com.example.beachvolleyapp.api;

import com.example.beachvolleyapp.model.Team;
import com.example.beachvolleyapp.service.TeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamRestController {

    TeamServices teamServices;

    @Autowired
    public TeamRestController(TeamServices teamServices) {
        this.teamServices = teamServices;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getTeams(){
        List<Team> teams = teamServices.findAll();
        return teams;
    }
}
