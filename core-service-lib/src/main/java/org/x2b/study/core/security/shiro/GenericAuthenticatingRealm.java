package org.x2b.study.core.security.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.x2b.study.core.security.data.mongodb.AuthenticatedUser;
import org.x2b.study.core.security.data.mongodb.AuthorizationRepository;

import java.util.UUID;

@Component
public class GenericAuthenticatingRealm implements Realm {

    @Autowired
    private AuthorizationRepository repository;

    @Override
    public String getName() {
        return "AuthenticatingRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JWTAuthenticationToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTAuthenticationToken jwtToken = (JWTAuthenticationToken) authenticationToken;

        UUID uuid = decodeUserId(jwtToken);
        AuthenticatedUser user = repository.findOne(uuid); //TODO: handle missing case

        SimpleAccount account = new SimpleAccount();
        account.setCredentials(jwtToken.getCredentials());
        account.setStringPermissions(user.getPermissions());
        account.setPrincipals(createPrincipalCollection(user));

        return account;
    }


    private PrincipalCollection createPrincipalCollection(AuthenticatedUser user) {
        SimplePrincipalCollection collection = new SimplePrincipalCollection();
        collection.add(user.getUUID(), getName());
        return collection;
    }


    private UUID decodeUserId(JWTAuthenticationToken token) {
        return null;
    }
}
