package com.example.beachvolleyapp.repository;

import com.example.beachvolleyapp.model.Tournament;
import com.example.beachvolleyapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findAllByUser(User user);

    List<Tournament> findFirstById(Long id);
}
