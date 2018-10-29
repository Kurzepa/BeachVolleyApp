package com.example.beachvolleyapp.repository;

import com.example.beachvolleyapp.model.TeamInTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamInTournamentRepository extends JpaRepository<TeamInTournament, Long> {

}
