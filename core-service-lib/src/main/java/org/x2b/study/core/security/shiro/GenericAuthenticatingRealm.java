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
import org.x2b.study.core.ServiceConstants;
import org.x2b.study.core.security.User;
import org.x2b.study.core.security.data.mongodb.AuthenticatedUser;
import org.x2b.study.core.security.data.mongodb.AuthorizationRepository;
import org.x2b.study.core.security.jwt.JWTUserRepository;

import java.util.UUID;

@Component
public class GenericAuthenticatingRealm implements Realm {

    @Autowired
    private AuthorizationRepository repository;

    private final JWTUserRepository jwtUserRepository;

    public GenericAuthenticatingRealm() {
        this.jwtUserRepository = new JWTUserRepository(); //TODO: this is akward for what amounts to one method
        //TODO: maybe have the JWT token decode itself so that it can actually expose and principle and
        //TODO: a credential
    }

    @Override
    public String getName() {
        return ServiceConstants.SECURITY_AUTHENTICATION_REALM_NAME;
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JWTAuthenticationToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTAuthenticationToken jwtToken = (JWTAuthenticationToken) authenticationToken;

        User claimedUser = jwtUserRepository.getUser(jwtToken);
        AuthenticatedUser user = repository.findOne(claimedUser.getUUID()); //TODO: handle missing case

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
