package com.scm.smart_contact_manager.service;

import com.scm.smart_contact_manager.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    Optional<User> getUserById(String userId);
    User getUserByEmail(String email);
    Optional<User> updateUser(User user);
    void deleteUser(String userId);
    List<User> getAllUsers();
}
