package com.example.beachvolleyapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Organizer implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private User userId;
    private Tournament tournamentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return userId;
    }

    public void setUser_id(User user_id) {
        this.userId = userId;
    }

    public Tournament getTournament_id() {
        return tournamentId;
    }

    public void setTournament_id(Tournament tournament_id) {
        this.tournamentId = tournamentId;
    }

    public Organizer() {
    }
}
