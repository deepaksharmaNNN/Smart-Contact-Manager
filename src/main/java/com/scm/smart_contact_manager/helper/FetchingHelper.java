package com.scm.smart_contact_manager.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.security.Principal;

public class FetchingHelper {

    public static String fetchEmailOfLoggedInUser(Authentication authentication){
        // Fetch email of logged-in user when logged in using Email and Password
        // Fetch email of logged-in user when logged in using Google
        // Fetch email of logged-in user when logged in using GitHub

        if(authentication instanceof OAuth2AuthenticatedPrincipal){
            var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            if(clientId.equalsIgnoreCase("google")){
                return oAuth2AuthenticationToken.getPrincipal().getAttribute("email");
            }else if(clientId.equalsIgnoreCase("github")){
                return oAuth2AuthenticationToken.getPrincipal().getAttribute("email") != null
                        ? oAuth2AuthenticationToken.getPrincipal().getAttribute("email")
                        : oAuth2AuthenticationToken.getPrincipal().getAttribute("login") + "@gmail.com";
            }
        }
        return authentication.getName();
    }

}
