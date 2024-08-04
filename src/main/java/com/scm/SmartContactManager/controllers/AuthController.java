package com.scm.SmartContactManager.controllers;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/auth/verify-email")
    public String verifyEmail(@RequestParam("token") String token, @RequestParam("email") String email) {
        System.out.println("Verifying Email");
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            if(user.getEmailToken().equals(token)) {
                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepository.save(user);
                return "user/success_page";
            }
            return "user/error_page";
        }
        return "user/error_page";
    }
}
