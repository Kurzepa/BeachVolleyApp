package com.example.beachvolleyapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Tournament implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date time;
    private String category;
    private Integer sitesNumber;
    private Integer fee;
    private String description;

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

    public Tournament(Location location){
        this.location = location;
    }

    public Tournament(String name, Date date, Date time, String category, Integer sitesNumber, Integer fee, Location location, User user) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.category = category;
        this.sitesNumber = sitesNumber;
        this.fee = fee;
        this.location = location;
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSitesNumber() {
        return sitesNumber;
    }

    public void setSitesNumber(Integer sitesNumber) {
        this.sitesNumber = sitesNumber;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
