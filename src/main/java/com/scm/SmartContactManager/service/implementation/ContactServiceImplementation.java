package com.scm.SmartContactManager.service.implementation;

import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.forms.ContactForm;
import com.scm.SmartContactManager.repository.ContactRepository;
import com.scm.SmartContactManager.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImplementation implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public void delete(String id) {
        contactRepository.deleteById(id);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepository.findByUser(userId);
    }

    @Override
    public Contact update(String contactId, ContactForm contactForm) {
        Contact oldContact = contactRepository.findById(contactId).get();
        oldContact.setName(contactForm.getName());
        oldContact.setEmail(contactForm.getEmail());
        oldContact.setAddress(contactForm.getAddress());
        oldContact.setFavorite(contactForm.isFavorite());
        oldContact.setWebsiteLink(contactForm.getWebsiteLink());
        oldContact.setPhoneNumber(contactForm.getPhoneNumber());
        oldContact.setLinkedInLink(contactForm.getLinkedInLink());
        oldContact.setDescription(contactForm.getDescription());
        return contactRepository.save(oldContact);
    }
}
