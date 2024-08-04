package com.scm.SmartContactManager.controllers;

import com.scm.SmartContactManager.forms.HelpForm;
import com.scm.SmartContactManager.forms.UserForm;
import com.scm.SmartContactManager.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelpMailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send-mail")
    public String sendMail(@ModelAttribute HelpForm helpForm){
        String name = helpForm.getName();
        String about = helpForm.getAbout();
        String message = helpForm.getMessage();
        emailService.sendEmail("tanaydwivedi095@gmail.com", about, name+"\n"+message);
        return "redirect:/home";
    }
}
