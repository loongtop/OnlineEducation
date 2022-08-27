package com.gkhy.serviceoauth2.oauth2.user;

import java.util.Map;


public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    private static final long serialVersionUID = 3530985985516707500L;

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
