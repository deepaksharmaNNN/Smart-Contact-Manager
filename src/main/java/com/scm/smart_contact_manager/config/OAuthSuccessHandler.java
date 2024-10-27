package com.scm.smart_contact_manager.config;

import com.scm.smart_contact_manager.enums.Providers;
import com.scm.smart_contact_manager.model.User;
import com.scm.smart_contact_manager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("OAuth Success Handler invoked.");

        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        // Retrieve user details from OAuth attributes
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String profileImageLink = oAuth2User.getAttribute("picture");
        String providerId = oAuth2User.getName();

        if (email == null || name == null) {
            log.error("Missing essential OAuth2 attributes: email or name is null");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid OAuth response.");
            return;
        }

        // Check if user exists and update or create accordingly
        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            log.info("User already exists, updating information for user: {}", email);
            existingUser.setName(name);
            existingUser.setProfileImageLink(profileImageLink);
            existingUser.setProviderId(providerId);
            existingUser.setEnabled(true);
            existingUser.setEmailVerified(true);
            existingUser.setProvider(Providers.GOOGLE);
            userService.updateUser(existingUser);
        } else {
            log.info("Creating a new user for email: {}", email);
            User newUser = User.builder()
                    .email(email)
                    .name(name)
                    .profileImageLink(profileImageLink)
                    .provider(Providers.GOOGLE)
                    .userId(UUID.randomUUID().toString())
                    .enabled(true)
                    .role("USER")
                    .emailVerified(true)
                    .providerId(providerId)
                    .about("This account was created using Google OAuth")
                    .build();
            userService.updateUser(newUser);
        }

        // Redirect to the dashboard
        log.info("Redirecting to /user/dashboard");
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }
}
