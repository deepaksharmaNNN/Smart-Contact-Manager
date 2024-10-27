// src/main/java/com/scm/smart_contact_manager/service/UserService.java
package com.scm.smart_contact_manager.service;

import com.scm.smart_contact_manager.exception.ResourceNotFoundException;
import com.scm.smart_contact_manager.mapper.UserMapper;
import com.scm.smart_contact_manager.model.User;
import com.scm.smart_contact_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        User newUser = UserMapper.mapUserToUser(user);
        newUser.setEnabled(true);
        newUser.setRole("USER");
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("User saved successfully");
        userRepository.save(newUser);
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void updateUser(User user) {
        if (user.getPassword() != null) { // Only encode if password is not null
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }


    public void deleteUser(String userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}