package com.example.beachvolleyapp.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Login nie może być pusty!")
    private String login;
    @NotEmpty(message = "Podaj hasło.")
    private String password;
    @NotEmpty(message = "Podaj imię.")
    private String name;
    @NotEmpty(message = "Podaj nazwisko.")
    private String surname;
    @Min(value = 3, message = "Minimalna wartość 3.")
    private Integer age;
    @NotEmpty(message = "Podaj płeć.")
    private String gender;
    @NotEmpty(message = "Podaj email.")
    @Email(message = "To nie jest adres email. Podaj poprawny adres.")
    private String email;
    private int points;


    @OneToMany(mappedBy = "user" )
    private List<Tournament> tournaments;

    @ManyToMany(mappedBy = "users")
    private List<Team> teams;


    public User() {
    }

    public User(@NotEmpty String login, @NotEmpty @Size(min = 6, max = 15) String password, @NotEmpty String name, @NotEmpty String surname, @Min(0) Integer age, @NotEmpty String gender, @NotEmpty @Email String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
