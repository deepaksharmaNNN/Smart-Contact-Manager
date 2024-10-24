package com.scm.smart_contact_manager.forms;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserForm {
    String name;
    String email;
    String password;
    String phoneNumber;
    String about;
}
