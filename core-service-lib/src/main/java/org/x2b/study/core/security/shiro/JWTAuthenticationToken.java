package org.x2b.study.core.security.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTAuthenticationToken implements AuthenticationToken{

    private final String data; //TODO: eventually we need to extract the user info from this token

    public JWTAuthenticationToken(String data) {
        this.data = data;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getToken() {
        return data;
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other.getClass().equals(JWTAuthenticationToken.class)) {
            return this.data.equals(((JWTAuthenticationToken) other).data);
        }
        return false;
    }
}
