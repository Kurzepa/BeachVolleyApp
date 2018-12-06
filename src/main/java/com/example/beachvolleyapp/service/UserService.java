package com.example.beachvolleyapp.service;

import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.TemporalAmount;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void save (User user){
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User findByLogin(String name) {
        return userRepository.findByLogin(name);
    }
}
