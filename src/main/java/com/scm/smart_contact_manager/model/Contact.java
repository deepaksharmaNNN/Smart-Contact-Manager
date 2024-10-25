package com.scm.smart_contact_manager.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
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
