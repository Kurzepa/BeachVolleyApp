//package com.example.beachvolleyapp;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.List;
//
//@Entity
//public class Organizer implements Serializable {
//
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToMany(mappedBy = "organizer")
//    private List<Tournament> tournaments;
//
//    public Organizer() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public List<Tournament> getTournaments() {
//        return tournaments;
//    }
//
//    public void setTournaments(List<Tournament> tournaments) {
//        this.tournaments = tournaments;
//    }
//}
