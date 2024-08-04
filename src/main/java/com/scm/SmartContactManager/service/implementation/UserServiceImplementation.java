package com.scm.SmartContactManager.service.implementation;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.forms.UserForm;
import com.scm.SmartContactManager.helper.AppConstants;
import com.scm.SmartContactManager.helper.EmailHelper;
import com.scm.SmartContactManager.helper.Message;
import com.scm.SmartContactManager.helper.MessageType;
import com.scm.SmartContactManager.repository.UserRepository;
import com.scm.SmartContactManager.service.interfaces.EmailService;
import com.scm.SmartContactManager.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        String emailToken = UUID.randomUUID().toString();
        user.setEmailToken(emailToken);
        User savedUser = userRepository.save(user);
        String emailLink = EmailHelper.getLinkForEmailVerification(user.getEmailToken(), user.getEmail());
        emailService.sendEmail(savedUser.getEmail(), "Verify Account", emailLink);
        return savedUser;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        User oldUser = getUserById(user.getUserId()).orElseThrow(()-> new RuntimeException("User not found"));
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setAbout(user.getAbout());
        oldUser.setPassword(user.getPassword());
        oldUser.setProfilePic(user.getProfilePic());
        oldUser.setEnabled(user.isEnabled());
        oldUser.setEmailVerified(user.isEmailVerified());
        oldUser.setPhoneVerified(user.isPhoneVerified());
        oldUser.setProvider(user.getProvider());
        oldUser.setProviderId(user.getProviderId());
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isUserExists(String id) {
        User user = getUserById(id).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user!=null ? true : false;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
