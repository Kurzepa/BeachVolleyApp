package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.User;
import com.example.beachvolleyapp.repository.UserRepository;
import com.example.beachvolleyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);
        }
        List<User> allUsers = userService.findAllByOrderByPointsDesc();
        model.addAttribute("users", allUsers);

        return"ranking";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser (@ModelAttribute @Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "register";
        }
        else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return "register_success";
        }

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
