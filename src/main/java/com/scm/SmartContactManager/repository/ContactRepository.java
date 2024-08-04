package com.scm.SmartContactManager.repository;

import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c where c.user.userId=:userId")
    List<Contact> findByUser(@Param("userId") String userId);
}
