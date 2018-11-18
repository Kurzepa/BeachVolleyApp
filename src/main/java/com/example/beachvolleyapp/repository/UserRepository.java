package com.example.beachvolleyapp.repository;

import com.example.beachvolleyapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    List<User> findAllByOrderByPointsDesc();

    User findByLogin(String login);
}
