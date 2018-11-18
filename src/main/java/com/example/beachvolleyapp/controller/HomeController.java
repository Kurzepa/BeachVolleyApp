package com.example.beachvolleyapp.controller;

import com.example.beachvolleyapp.model.Tournament;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            String name = authentication.getName();
            model.addAttribute("name", name);
        }

        return "index";
    }

//    @GetMapping("/index_login")
//    public String zalogowany(Model model){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication != null && authentication.isAuthenticated() &&
//                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
//            String name = authentication.getName();
//            model.addAttribute("name", name);
//            System.out.println(name);
//        }
//        return "index_login";
//    }
}
