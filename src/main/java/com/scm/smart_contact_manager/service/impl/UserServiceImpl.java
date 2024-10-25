package com.scm.smart_contact_manager.service.impl;

import com.scm.smart_contact_manager.exception.ResourceNotFoundException;
import com.scm.smart_contact_manager.mapper.UserMapper;
import com.scm.smart_contact_manager.model.User;
import com.scm.smart_contact_manager.repository.UserRepository;
import com.scm.smart_contact_manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> updateUser(User user) {
        // fetch the user from the database
        userRepository.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));;
        // update the user
        User foundUser = UserMapper.mapUserToUser(user);
        return Optional.of(userRepository.save(foundUser));
    }

    @Override
    public void deleteUser(String userId) {
        //check if user exists
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
