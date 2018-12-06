package com.example.beachvolleyapp.service;

import com.example.beachvolleyapp.model.Team;
import com.example.beachvolleyapp.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.TemporalAmount;
import java.util.List;

@Service
public class TeamServices {

    @Autowired
    TeamRepository teamRepository;

    public Team findById(Long teamId) {
        return teamRepository.findById(teamId).get();
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

    public List<Team> findAll() {
       return teamRepository.findAll();
    }
}
