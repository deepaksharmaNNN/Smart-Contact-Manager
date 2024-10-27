package com.scm.smart_contact_manager.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min = 2,message = "Name should be at least 2 characters")
    String name;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Password is required")
    @Size(min = 5,message = "Password should be at least 5 characters")
    String password;

    @NotBlank(message = "Phone number is required")
    String phoneNumber;


    String about;
}
