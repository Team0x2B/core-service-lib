package org.x2b.study.core.security.jwt;

import org.x2b.study.core.security.User;
import org.x2b.study.core.security.shiro.JWTAuthenticationToken;

import java.util.UUID;

public class JWTUserRepository {

    private boolean validateToken(JWTAuthenticationToken token) {
        return true;
    }

    public User getUser(JWTAuthenticationToken token) {
        if (validateToken(token)) {
            return () -> UUID.fromString((String) token.getPrincipal()); //TODO: this is not a good place for a lambda
        }
        return null;
    }
}
