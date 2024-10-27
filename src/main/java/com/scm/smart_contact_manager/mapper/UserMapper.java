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
                .about(user.getAbout())
                .profileImageLink(user.getProfileImageLink())
                .phoneNumber(user.getPhoneNumber())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
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
