package com.scm.smart_contact_manager.controller;

import com.scm.smart_contact_manager.helper.FetchingHelper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    // User dashboard
    @GetMapping("/dashboard")
    public String UserDashboard() {
        return "user/dashboard"; // this will look for dashboard.html in templates folder
    }
    // User view profile
    @GetMapping("/profile")
    public String UserProfile(Authentication authentication){
        String email = FetchingHelper.fetchEmailOfLoggedInUser(authentication);
        log.info("Email: {}", email);
        return "user/profile"; // this will look for profile.html in templates folder
    }

    // User add contact

    // User view contact

    // User update contact

    // User delete contact

    // User search contact



}
