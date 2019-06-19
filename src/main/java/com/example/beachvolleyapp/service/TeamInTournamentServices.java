package com.example.beachvolleyapp.service;

import com.example.beachvolleyapp.model.TeamInTournament;
import com.example.beachvolleyapp.repository.TeamInTournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamInTournamentServices {

    @Autowired
    TeamInTournamentRepository teamInTournamentRepository;

    public void save(TeamInTournament teamInTournament) {
        teamInTournamentRepository.save(teamInTournament);
    }

    public List<TeamInTournament> findAll(){
        return teamInTournamentRepository.findAll();
    }
}
