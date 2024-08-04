package com.scm.SmartContactManager.controllers;

import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.forms.ContactForm;
import com.scm.SmartContactManager.helper.Message;
import com.scm.SmartContactManager.helper.MessageType;
import com.scm.SmartContactManager.service.interfaces.ContactService;
import com.scm.SmartContactManager.service.interfaces.ImageService;
import com.scm.SmartContactManager.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/addContact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Principal principal, HttpSession session){
        if(bindingResult.hasErrors()){
            session.setAttribute("message", Message.builder().content("Correct the errors").type(MessageType.red).build());
            return "user/addContact";
        }
        String fileURL = imageService.uploadImage(contactForm.getContactImage());
        Contact contact = new Contact();
        User user = userService.getUserByEmail(principal.getName()).get();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setFavorite(contactForm.isFavorite());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contactService.save(contact);
        session.setAttribute("message", Message.builder().content("Contact added successfully").type(MessageType.green).build());
        return "redirect:/user/contact/add";
    }

    @GetMapping("")
    public String viewContacts(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.getUserByEmail(username).get();
        List<Contact> contactList = contactService.getByUserId(user.getUserId());
        model.addAttribute("contactList", contactList);
        return "user/contacts";
    }

    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable String contactId){
        contactService.delete(contactId);
        System.out.println("Contact deleted successfully");
        return "redirect:/user/contact";
    }

    @GetMapping("/view/{contactId}")
    public String updateContact(@PathVariable String contactId, Model model){
        Contact contact = contactService.getById(contactId);
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setAddress(contact.getAddress());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setLinkedInLink(contact.getLinkedInLink());
        model.addAttribute("contactForm", contactForm);
        return "user/update_contact_view";
    }

    @PostMapping("/update/{contactId}")
    public String updateContact(@PathVariable String contactId, @Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/update_contact_view";
        }
        contactService.update(contactId, contactForm);
        model.addAttribute("message", Message.
                builder()
                .content("Contact updated successfully")
                .type(MessageType.green)
                .build());
        return "redirect:/user/contact";
    }
}
