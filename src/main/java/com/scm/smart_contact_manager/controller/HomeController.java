package com.scm.smart_contact_manager.controller;

import com.scm.smart_contact_manager.enums.MessageType;
import com.scm.smart_contact_manager.forms.UserForm;
import com.scm.smart_contact_manager.helper.Message;
import com.scm.smart_contact_manager.mapper.UserMapper;
import com.scm.smart_contact_manager.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home() {
        return "home";  // this will look for home.html in templates folder
    }
    @GetMapping("/base")
    public String base() {
        return "base";  // this will look for base.html in templates folder
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";  // this will look for contact.html in templates folder
    }
    @GetMapping("/services")
    public String services() {
        return "services";  // this will look for services.html in templates folder
    }

    @GetMapping("/about")
    public String about() {
        return "about";  // this will look for about.html in templates folder
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";  // this will look for register.html in templates folder
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // this will look for login.html in templates folder
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute UserForm userForm, HttpSession session) {
        // fetch data from request
        // process data
        // verify data
        // if data is correct, save to database
        userService.saveUser(UserMapper.mapUserFormToUser(userForm));
        System.out.println("User saved successfully");
        // add success message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        // remove message from session

        // redirect to login page
        return "redirect:/register";  // this will look for register.html in templates folder
    }

}