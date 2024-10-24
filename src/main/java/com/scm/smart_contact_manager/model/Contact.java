package com.scm.smart_contact_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact {

    @Id
    String contactId;
    String name;
    String email;
    String phoneNumber;
    String imageLink;
    String description;
    boolean isFavourite = false;

    @ManyToOne
    User user;

}
