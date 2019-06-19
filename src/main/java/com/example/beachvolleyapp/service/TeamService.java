package com.example.beachvolleyapp.service;

import com.example.beachvolleyapp.model.Team;
import com.example.beachvolleyapp.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Optional<Team> findById(Long teamId) {
        return teamRepository.findById(teamId);
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> findAll() {
       return teamRepository.findAll();
    }
}
