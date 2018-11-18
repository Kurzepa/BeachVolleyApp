package com.example.beachvolleyapp.service;

import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void save (User user){
        userRepository.save(user);
    }
}
