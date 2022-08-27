package com.gkhy.serviceoauth2.oauth2.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo implements Serializable {
    protected Map<String, Object> attributes;

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public abstract String getId();
    public abstract String getImageUrl();
}
