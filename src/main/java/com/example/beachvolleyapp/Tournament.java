package com.example.beachvolleyapp;

import javafx.scene.chart.PieChart;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Tournament implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String category;
    private int sitesNumber;
    private int fee;

    /*
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;
    */

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "tournament" )
    List<Score> scores;

    @OneToMany(mappedBy = "tournament")
    List<TeamInTournament> teamInTournaments;


    public Tournament() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

//    public Organizer getOrganizer() {
//        return organizer;
//    }
//
//    public void setOrganizer(Organizer organizer) {
//        this.organizer = organizer;
//    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSitesNumber() {
        return sitesNumber;
    }

    public void setSitesNumber(int sitesNumber) {
        this.sitesNumber = sitesNumber;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
