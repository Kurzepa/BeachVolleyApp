package com.example.beachvolleyapp.api;

import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.service.TournamentServices;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {

    TournamentServices tournamentServices;

    @Autowired
    public TournamentRestController(TournamentServices tournamentServices) {
        this.tournamentServices = tournamentServices;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tournament> getTournaments(){
        return tournamentServices.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Tournament> getTournament(@PathVariable Long id){
        return tournamentServices.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTournament(@RequestBody Tournament tournament){
        tournament = tournamentServices.save(tournament);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(tournament.getId())
            .toUri();
        return ResponseEntity.created(location).body(tournament);
    }

}
