package com.scm.smart_contact_manager.config;

import com.scm.smart_contact_manager.enums.Providers;
import com.scm.smart_contact_manager.model.User;
import com.scm.smart_contact_manager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

        // Identify the provider
        var provider = (OAuth2AuthenticationToken) authentication;
        String providerName = provider.getAuthorizedClientRegistrationId();

        var OAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRole("USER");
        user.setEnabled(true);
        user.setEmailVerified(true);

        if(providerName.equalsIgnoreCase("google")){
            user.setEmail(OAuth2User.getAttribute("email"));
            user.setProfileImageLink(OAuth2User.getAttribute("picture"));
            user.setName(OAuth2User.getAttribute("name"));
            user.setProviderId(OAuth2User.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This account was created using Google");
        }else if(providerName.equalsIgnoreCase("github")){
            String email = OAuth2User.getAttribute("email") != null
                    ? OAuth2User.getAttribute("email")
                    : OAuth2User.getAttribute("login") + "@gmail.com";
            String profileImageLink = OAuth2User.getAttribute("avatar_url");
            String name = OAuth2User.getAttribute("name") != null
                    ? OAuth2User.getAttribute("name")
                    : OAuth2User.getAttribute("login");

            user.setEmail(email);
            user.setProfileImageLink(profileImageLink);
            user.setName(name);
            user.setProviderId(OAuth2User.getName());
            user.setProvider(Providers.GITHUB);
            user.setAbout("This account was created using GitHub");
        }else {
            log.error("Invalid OAuth2 provider: {}", providerName);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid OAuth2 provider.");
            return;
        }


        // Check if user exists and update or create accordingly
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            user.setUserId(existingUser.get().getUserId());
            userService.updateUser(user);
        }else {
            userService.updateUser(user);
        }

        // Redirect to the dashboard
        log.info("Redirecting to /user/dashboard");
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }
}
