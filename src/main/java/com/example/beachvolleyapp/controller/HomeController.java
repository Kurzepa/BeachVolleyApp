package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.Tournament;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        return "index";
    }

    @GetMapping("/index_login")
    public String zalogowany(Model model){
        return "index_login";
    }
}
