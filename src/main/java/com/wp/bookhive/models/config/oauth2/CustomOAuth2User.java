package com.wp.bookhive.models.config.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private String oauth2ClientName;
    private OAuth2User oauth2User;

    public CustomOAuth2User(OAuth2User oauth2User, String oauth2ClientName) {
        super();
        this.oauth2User = oauth2User;
        this.oauth2ClientName = oauth2ClientName;
    }

    public CustomOAuth2User() {
        super();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

    public String getEmail() {
        return oauth2User.getAttribute("email");
    }

    public String getOauth2ClientName() {
        return this.oauth2ClientName;
    }
}
