package com.example.beachvolleyapp.api;

import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.UserRepository;
import com.example.beachvolleyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsersRanking(){
        List<User> users = userService.findAllByOrderByPointsDesc();
        return users;
    }

}
