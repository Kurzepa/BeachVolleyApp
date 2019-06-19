package com.example.beachvolleyapp.api;

import com.example.beachvolleyapp.model.Team;
import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.UserRepository;
import com.example.beachvolleyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody User user){
        //if() { Dostepna walidacja
        user = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
    }

}
