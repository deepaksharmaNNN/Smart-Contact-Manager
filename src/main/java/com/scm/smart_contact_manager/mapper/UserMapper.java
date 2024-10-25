package com.scm.smart_contact_manager.mapper;

import com.scm.smart_contact_manager.forms.UserForm;
import com.scm.smart_contact_manager.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User mapUserToUser(User user) {
        return User.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .about(user.getAbout())
                .profileImageLink(user.getProfileImageLink())
                .phoneNumber(user.getPhoneNumber())
                .enabled(user.isEnabled())
                .emailVerified(user.isEmailVerified())
                .phoneVerified(user.isPhoneVerified())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .contacts(user.getContacts())
                .build();
    }
    public User mapUserFormToUser(UserForm userForm) {
        return User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .about(userForm.getAbout())
                .phoneNumber(userForm.getPhoneNumber())
                .build();
    }
}
