package com.scm.SmartContactManager.controllers;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.service.interfaces.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUser(Model model, Principal principal) {
        if (principal != null) {
            System.out.println("Adding logged in user");
            String name = principal.getName();
            User user = userService
                    .getUserByEmail(name)
                    .orElse(null);
            model.addAttribute("loggedInUser", user);
        }
    }
}
