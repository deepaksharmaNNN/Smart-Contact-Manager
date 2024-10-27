package com.scm.smart_contact_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    // User dashboard
    @GetMapping("/dashboard")
    public String UserDashboard() {
        return "user/dashboard"; // this will look for dashboard.html in templates folder
    }
    // User view profile
    @GetMapping("/profile")
    public String UserProfile() {
        return "user/profile"; // this will look for profile.html in templates folder
    }

    // User add contact

    // User view contact

    // User update contact

    // User delete contact

    // User search contact


}
