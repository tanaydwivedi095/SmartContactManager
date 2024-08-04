package com.scm.SmartContactManager.controllers;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.service.implementation.UserServiceImplementation;
import com.scm.SmartContactManager.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

//    @GetMapping("/dashboard")
//    public String userDashboard(Model model) {
//        return "user/dashboard";
//    }

    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }
}
