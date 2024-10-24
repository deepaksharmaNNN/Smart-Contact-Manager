package com.scm.smart_contact_manager.repository;

import com.scm.smart_contact_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
