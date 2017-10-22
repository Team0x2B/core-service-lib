package org.x2b.studi.core.security.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.x2b.studi.core.security.User;
import org.x2b.studi.core.security.data.mongodb.AuthenticatedUser;
import org.x2b.studi.core.security.data.mongodb.AuthorizationRepository;
import org.x2b.studi.core.security.jwt.JWTUserTokenVerifier;
import org.x2b.studi.core.ServiceConstants;

@Component
public class GenericAuthenticatingRealm extends AuthorizingRealm {


    @Autowired
    public AuthorizationRepository repository;

    @Autowired
    private JWTUserTokenVerifier jwtUserTokenVerifier;

    public GenericAuthenticatingRealm() {
        this.setCachingEnabled(false); //TODO: maybe just don't extend a caching realm
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
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTAuthenticationToken jwtToken = (JWTAuthenticationToken) authenticationToken;

        User claimedUser = jwtUserTokenVerifier.getUser(jwtToken);
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(claimedUser, authenticationToken.getCredentials(), getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        AuthenticatedUser authorizedUser = repository.findOne(user.getUUID());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(authorizedUser.getPermissions());
        return info;
    }
}
