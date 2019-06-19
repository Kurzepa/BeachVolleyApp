package com.example.beachvolleyapp.service;

import com.example.beachvolleyapp.model.Team;
import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentServices {

    @Autowired
    TournamentRepository tournamentRepository;

    public Optional<Tournament> findById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId);
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    public List<Tournament> findAllByUser(User byLogin) {
        return tournamentRepository.findAllByUser(byLogin);
    }

    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }
}
