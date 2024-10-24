package com.scm.smart_contact_manager.service;

import com.scm.smart_contact_manager.model.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(String userId);
    User getUserByEmail(String email);
    User updateUser(User user);
    void deleteUser(String userId);
}
