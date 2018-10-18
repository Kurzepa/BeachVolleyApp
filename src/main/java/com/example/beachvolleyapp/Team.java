package com.example.beachvolleyapp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team" )
    List<Score> scores;

    @OneToMany(mappedBy = "team")
    List<TeamInTournament> teamInTournaments;

    @ManyToMany
    private List<User> users;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public List<TeamInTournament> getTeamInTournaments() {
        return teamInTournaments;
    }

    public void setTeamInTournaments(List<TeamInTournament> teamInTournaments) {
        this.teamInTournaments = teamInTournaments;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
