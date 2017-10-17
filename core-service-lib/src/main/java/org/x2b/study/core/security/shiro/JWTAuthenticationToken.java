package org.x2b.study.core.security.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTAuthenticationToken implements AuthenticationToken{

    private final String data;

    public JWTAuthenticationToken(String data) {
        this.data = data;
    }

    @Override
    public Object getPrincipal() {
        return data;
    }

    @Override
    public Object getCredentials() {
        return data;
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
