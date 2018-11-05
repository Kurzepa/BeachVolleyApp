package com.example.beachvolleyapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Location implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int number;
    private String city;
    private String province;

    @OneToMany(mappedBy = "location")
    List<Tournament> tournaments;

    public Location() {
    }

    public Location(String street, int number, String city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
