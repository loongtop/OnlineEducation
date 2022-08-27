package com.gkhy.serviceoauth2.oauth2.user;


import com.gkhy.serviceoauth2.exception.OAuth2AuthenticationProcessingException;
import com.gkhy.serviceoauth2.model.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {

        if (AuthProvider.GOOGLE.name().equalsIgnoreCase(registrationId)) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (AuthProvider.FACEBOOK.name().equalsIgnoreCase(registrationId)) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (AuthProvider.GITHUB.name().equalsIgnoreCase(registrationId)) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
