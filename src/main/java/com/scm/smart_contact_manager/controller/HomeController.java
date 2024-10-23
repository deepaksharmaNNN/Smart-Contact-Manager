package com.scm.smart_contact_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";  // this will look for home.html in templates folder
    }
    @GetMapping("/base")
    public String base() {
        return "base";  // this will look for base.html in templates folder
    }

    @GetMapping("/about")
    public String about() {
        return "about";  // this will look for about.html in templates folder
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";  // this will look for signup.html in templates folder
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // this will look for login.html in templates folder
    }

}