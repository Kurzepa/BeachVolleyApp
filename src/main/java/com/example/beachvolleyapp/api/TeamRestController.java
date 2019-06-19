package com.example.beachvolleyapp.api;

import com.example.beachvolleyapp.model.Team;
import com.example.beachvolleyapp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamRestController {

    TeamService teamService;

    @Autowired
    public TeamRestController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/all")
    public List<Team> getTeams(){
        List<Team> teams = teamService.findAll();
        return teams;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable Long id) {
        return teamService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTeam(@RequestBody Team team){
        //if() { Dostepna walidacja
        System.out.println(team);
            team = teamService.save(team);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(team.getId())
                    .toUri();
            return ResponseEntity.created(location).body(team);
//        } else {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
    }
}
