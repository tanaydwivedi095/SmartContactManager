package com.scm.SmartContactManager.service.interfaces;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.forms.UserForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    User updateUser(User user);
    void deleteUser(String id);
    boolean isUserExists(String id);
    List<User> getAllUsers();
    boolean isUserExistsByEmail(String email);
    Optional<User> getUserByEmail(String email);
}
