package com.scm.SmartContactManager.controllers;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.forms.HelpForm;
import com.scm.SmartContactManager.forms.UserForm;
import com.scm.SmartContactManager.helper.Message;
import com.scm.SmartContactManager.helper.MessageType;
import com.scm.SmartContactManager.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }
    
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @RequestMapping("/service")
    public String servicePage(){
        return "service";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/signup")
    public String signupPage(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    @RequestMapping("/contact")
    public String contactPage(Model model){
        HelpForm helpForm = new HelpForm();
        model.addAttribute("helpForm", helpForm);
        return "contact";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()) {
            return "signup";
        }
        User newUser = new User();
        newUser.setName(userForm.getName());
        newUser.setEmail(userForm.getEmail());
        newUser.setPassword(userForm.getPassword());
        newUser.setPhoneNumber(userForm.getPhone());
        newUser.setAbout(userForm.getAbout());
        userService.saveUser(newUser);
        Message message = Message.builder().content("Registration Successful!").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/signup";
    }
}
