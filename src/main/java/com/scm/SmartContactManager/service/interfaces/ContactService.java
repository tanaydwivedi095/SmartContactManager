package com.scm.SmartContactManager.service.interfaces;

import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.forms.ContactForm;

import java.util.List;

public interface ContactService {
    Contact save(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);
    List<Contact> search(String name, String email, String phoneNumber);
    List<Contact> getByUserId(String userId);

    Contact update(String contactId, ContactForm contactForm);
}
