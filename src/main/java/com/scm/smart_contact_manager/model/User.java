package com.scm.smart_contact_manager.model;

import com.scm.smart_contact_manager.enums.Providers;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String userId;

    @Column(name = "user_name", nullable = false)
    String name;

    @Column(name = "user_email", nullable = false, unique = true)
    String email;
    String password;

    @Column(length = 1000)
    String about;

    String role;

    @Column(length = 5000)
    String profileImageLink;
    String phoneNumber;
    //information about the user
    boolean enabled = false;
    boolean emailVerified = false;
    boolean phoneVerified = false;

    // Self, Google, GitHub
    @Enumerated(EnumType.STRING)
    @Builder.Default
    Providers provider = Providers.SELF;
    String providerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Contact> contacts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.role.split(","))
                .map(SimpleGrantedAuthority :: new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
